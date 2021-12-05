import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { MasterService } from 'src/app/services/master.service';
import { DEFAULT_CURRENCY, FILTERS } from '../../utils/constants';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  @ViewChild('searchProductForm', {static: false}) searchProductForm: NgForm;

  searchQuery: string = null;
  query: string = null;
  productData: Array<any> = [];
  currencies: any = {};
  currentCurrency: string = DEFAULT_CURRENCY;
  filterJson: string = null;
  filters: Array<any> = [
    { order: FILTERS.ASC, column: 'price', name: 'Lowest Price' },
    { order: FILTERS.DESC, column: 'price', name: 'Highest Price' },

    { order: FILTERS.ASC, column: 'createdDate', name: 'Newly Added' },
    { order: FILTERS.DESC, column: 'createdDate', name: 'Oldest Added' },

    { order: FILTERS.ASC, column: 'lastModifiedDate', name: 'Recently Updated' },
    { order: FILTERS.DESC, column: 'lastModifiedDate', name: 'Least Updated' },
  ];
  
  constructor(private masterService: MasterService, private toastrService: ToastrService) { }

  ngOnInit() {
    this.searchProducts();
    this.getAllCurrencies();
  }

  searchProducts() {
    let currentFilter = (JSON.parse(this.filterJson)) ? JSON.parse(this.filterJson) : {};
    let query = this.searchQuery ? this.searchQuery : "";
    this.masterService.getProducts(query, currentFilter).subscribe(res => {
      this.productData = res;
      this.query = this.searchQuery;
      this.toastrService.success("Found " + this.productData.length +" cars");
      this.changeCurrency(this.currentCurrency);
    }, err => {
      this.toastrService.error("Unable to find cars due to error: " + err.message);
    });
  }

  getAllCurrencies() {
    this.masterService.getAllCurrencies().subscribe(res => {
      this.currencies = (res && res['symbols']) ? res['symbols'] : this.currencies;
      this.toastrService.success("Found " + Object.keys(this.currencies).length +" currencies");
    }, err => {
      this.toastrService.error("Unable to load all currencies due to error: " + err.message);
    });
  }

  changeCurrency(newCurrency: string) {
    let errorMsgShown: boolean = false;
    let successMsgShown: boolean = false;

    this.currentCurrency = newCurrency;
    if(this.productData && this.productData.length > 0) {
      this.productData.forEach(product => {
        this.masterService.convertCurrency(product.currencyValue, newCurrency).subscribe(res => {
          product.price = product.price * res['rates'][newCurrency];
          product.currencyValue = newCurrency;
          if(!successMsgShown) {
            this.toastrService.warning("Fetching updated currencies");
            successMsgShown = true;
          }
        }, err => {
          if(!errorMsgShown) {
            this.toastrService.error("Unable to update currencies due to error: " + err.message);
            errorMsgShown = true;
          }
        });
      });
    }
  }

  updateData(filter: string) {
    
  }

  resetSearch() {
    this.searchQuery = null;
    this.filterJson = null;
    this.searchProducts();
    this.changeCurrency(this.currentCurrency);
  }

}
