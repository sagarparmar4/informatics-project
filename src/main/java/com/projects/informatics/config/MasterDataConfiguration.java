package com.projects.informatics.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.projects.informatics.entities.BodyType;
import com.projects.informatics.entities.Color;
import com.projects.informatics.entities.FuelType;
import com.projects.informatics.entities.Manufacturer;
import com.projects.informatics.entities.Product;
import com.projects.informatics.repositories.BodyTypeRepository;
import com.projects.informatics.repositories.ColorRepository;
import com.projects.informatics.repositories.FuelTypeRepository;
import com.projects.informatics.repositories.ManufacturerRepository;
import com.projects.informatics.repositories.ProductRepository;

import one.util.streamex.StreamEx;

@Configuration
@ConditionalOnProperty(name = "cars.initialize-default-setup", havingValue = "true")
public class MasterDataConfiguration {

	@Autowired
	Environment environment;

	@Autowired
	BodyTypeRepository bodyTypeRepository;

	@Autowired
	ColorRepository colorRepository;

	@Autowired
	FuelTypeRepository fuelTypeRepository;

	@Autowired
	ManufacturerRepository manufacturerRepository;

	@Autowired
	ProductRepository productRepository;

	@PostConstruct
	public void setUpMasterData() {
		// Update practices
		StreamEx.of(this.getDefaultBodyTypes()).forEach(e -> {
			BodyType existingData = bodyTypeRepository.findByName(e.getName());
			if (existingData == null) {
				bodyTypeRepository.save(e);
			}
		});

		StreamEx.of(this.getDefaultColors()).forEach(e -> {
			Color existingData = colorRepository.findByName(e.getName());
			if (existingData == null) {
				colorRepository.save(e);
			}
		});

		StreamEx.of(this.getDefaultFuelType()).forEach(e -> {
			FuelType existingData = fuelTypeRepository.findByName(e.getName());
			if (existingData == null) {
				fuelTypeRepository.save(e);
			}
		});

		StreamEx.of(this.getDefaultManufacturer()).forEach(e -> {
			Manufacturer existingData = manufacturerRepository.findByName(e.getName());
			if (existingData == null) {
				manufacturerRepository.save(e);
			}
		});

		StreamEx.of(this.getDefaultProducts()).forEach(e -> {
			Product existingData = productRepository.findByModelName(e.getModelName());
			if (existingData == null) {
				productRepository.save(e);
			}
		});

	}

	private ImmutableList<BodyType> getDefaultBodyTypes() {

		Builder<BodyType> listBuilder = new ImmutableList.Builder<BodyType>();
		String activeProfile = environment.getActiveProfiles()[0].toUpperCase();

		switch (activeProfile) {
		case "DEV":
		case "SIT":
		case "UAT":
		case "PROD":
			listBuilder.add(new BodyType("CAMPER", "Camper"));
			listBuilder.add(new BodyType("CONVERTIBLE", "Convertible"));
			listBuilder.add(new BodyType("SUV", "SUV"));
			listBuilder.add(new BodyType("VAN", "Van"));
			break;

		default:
			throw new RuntimeException("Invalid Application Profile Found");
		}

		return listBuilder.build();
	}

	private ImmutableList<Color> getDefaultColors() {

		Builder<Color> listBuilder = new ImmutableList.Builder<Color>();
		String activeProfile = environment.getActiveProfiles()[0].toUpperCase();

		switch (activeProfile) {
		case "DEV":
		case "SIT":
		case "UAT":
		case "PROD":
			listBuilder.add(new Color("RED", "Red"));
			listBuilder.add(new Color("GREEN", "Green"));
			listBuilder.add(new Color("BLUE", "Blue"));
			listBuilder.add(new Color("WHITE", "White"));
			break;

		default:
			throw new RuntimeException("Invalid Application Profile Found");
		}

		return listBuilder.build();
	}

	private ImmutableList<FuelType> getDefaultFuelType() {

		Builder<FuelType> listBuilder = new ImmutableList.Builder<FuelType>();
		String activeProfile = environment.getActiveProfiles()[0].toUpperCase();

		switch (activeProfile) {
		case "DEV":
		case "SIT":
		case "UAT":
		case "PROD":
			listBuilder.add(new FuelType("PETROL", "Petrol"));
			listBuilder.add(new FuelType("GAS", "Gas"));
			listBuilder.add(new FuelType("ELECTRIC", "Electric"));
			listBuilder.add(new FuelType("HYDROGEN", "Hydrogen"));
			break;

		default:
			throw new RuntimeException("Invalid Application Profile Found");
		}

		return listBuilder.build();
	}

	private ImmutableList<Manufacturer> getDefaultManufacturer() {

		Builder<Manufacturer> listBuilder = new ImmutableList.Builder<Manufacturer>();
		String activeProfile = environment.getActiveProfiles()[0].toUpperCase();

		switch (activeProfile) {
		case "DEV":
		case "SIT":
		case "UAT":
		case "PROD":
			listBuilder.add(new Manufacturer("FORD", "Ford"));
			listBuilder.add(new Manufacturer("AUDI", "Audi"));
			listBuilder.add(new Manufacturer("HONDA", "Honda"));
			listBuilder.add(new Manufacturer("BMW", "BMW"));
			break;

		default:
			throw new RuntimeException("Invalid Application Profile Found");
		}

		return listBuilder.build();
	}

	private ImmutableList<Product> getDefaultProducts() {

		Builder<Product> listBuilder = new ImmutableList.Builder<Product>();
		String activeProfile = environment.getActiveProfiles()[0].toUpperCase();

		switch (activeProfile) {
		case "DEV":
		case "SIT":
		case "UAT":
		case "PROD":

			Manufacturer ford = manufacturerRepository.findByName("FORD");
			Manufacturer audi = manufacturerRepository.findByName("AUDI");
			Manufacturer honda = manufacturerRepository.findByName("HONDA");
			Manufacturer bmw = manufacturerRepository.findByName("BMW");

			Color red = colorRepository.findByName("RED");
			Color green = colorRepository.findByName("GREEN");
			Color blue = colorRepository.findByName("BLUE");
			Color white = colorRepository.findByName("WHITE");

			FuelType petrol = fuelTypeRepository.findByName("PETROL");
			FuelType gas = fuelTypeRepository.findByName("GAS");
			FuelType electric = fuelTypeRepository.findByName("ELECTRIC");
			FuelType hydrogen = fuelTypeRepository.findByName("HYDROGEN");

			BodyType camper = bodyTypeRepository.findByName("CAMPER");
			BodyType convertible = bodyTypeRepository.findByName("CONVERTIBLE");
			BodyType SUV = bodyTypeRepository.findByName("SUV");
			BodyType van = bodyTypeRepository.findByName("VAN");

			String defaultCurrencyValue = "EUR";

			String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
					+ "Vivamus in euismod quam. Ut elementum nunc ullamcorper purus vestibulum vulputate. "
					+ "Cras fringilla lacinia ipsum malesuada malesuada. Donec pellentesque ipsum et "
					+ "lectus cursus, et lobortis dolor pellentesque. Aliquam eget lorem nec nisi dignissim "
					+ "sodales non a ligula.";

			Product productA = new Product();
			productA.setModelName("Car A");
			productA.setYear(2018);
			productA.setPrice(2000D);
			productA.setCurrencyValue(defaultCurrencyValue);
			productA.setManufacturer(ford);
			productA.setColor(red);
			productA.setProductCode("PC01");
			productA.setDescription(description);
			productA.setDoors(2);
			productA.setMileage(10);
			productA.setFuelType(petrol);
			productA.setEngineSize(2);
			productA.setTransmission(4);
			productA.setBodyType(camper);

			Product productB = new Product();
			productB.setModelName("Car B");
			productB.setYear(2019);
			productB.setPrice(3000D);
			productA.setCurrencyValue(defaultCurrencyValue);
			productB.setManufacturer(audi);
			productB.setColor(green);
			productB.setProductCode("PC02");
			productB.setDescription(description);
			productB.setDoors(4);
			productB.setMileage(12);
			productB.setFuelType(gas);
			productB.setEngineSize(4);
			productB.setTransmission(4);
			productB.setBodyType(convertible);

			Product productC = new Product();
			productC.setModelName("Car C");
			productC.setYear(2020);
			productC.setPrice(4000D);
			productA.setCurrencyValue(defaultCurrencyValue);
			productC.setManufacturer(honda);
			productC.setColor(blue);
			productC.setProductCode("PC03");
			productC.setDescription(description);
			productC.setDoors(6);
			productC.setMileage(10);
			productC.setFuelType(electric);
			productC.setEngineSize(2);
			productC.setTransmission(6);
			productC.setBodyType(SUV);

			Product productD = new Product();
			productD.setModelName("Car D");
			productD.setYear(2021);
			productD.setPrice(5000D);
			productA.setCurrencyValue(defaultCurrencyValue);
			productD.setManufacturer(bmw);
			productD.setColor(white);
			productD.setProductCode("PC04");
			productD.setDescription(description);
			productD.setDoors(4);
			productD.setMileage(12);
			productD.setFuelType(hydrogen);
			productD.setEngineSize(2);
			productD.setTransmission(6);
			productD.setBodyType(van);

			Product productE = new Product();
			productE.setModelName("Car E");
			productE.setYear(2018);
			productE.setPrice(2000D);
			productA.setCurrencyValue(defaultCurrencyValue);
			productE.setManufacturer(ford);
			productE.setColor(red);
			productE.setProductCode("PC05");
			productE.setDescription(description);
			productE.setDoors(2);
			productE.setMileage(10);
			productE.setFuelType(petrol);
			productE.setEngineSize(2);
			productE.setTransmission(4);
			productE.setBodyType(camper);

			Product productF = new Product();
			productF.setModelName("Car F");
			productF.setYear(2019);
			productF.setPrice(3000D);
			productA.setCurrencyValue(defaultCurrencyValue);
			productF.setManufacturer(audi);
			productF.setColor(green);
			productF.setProductCode("PC06");
			productF.setDescription(description);
			productF.setDoors(4);
			productF.setMileage(12);
			productF.setFuelType(gas);
			productF.setEngineSize(4);
			productF.setTransmission(4);
			productF.setBodyType(convertible);

			Product productG = new Product();
			productG.setModelName("Car G");
			productG.setYear(2020);
			productG.setPrice(4000D);
			productA.setCurrencyValue(defaultCurrencyValue);
			productG.setManufacturer(honda);
			productG.setColor(blue);
			productG.setProductCode("PC07");
			productG.setDescription(description);
			productG.setDoors(6);
			productG.setMileage(10);
			productG.setFuelType(electric);
			productG.setEngineSize(2);
			productG.setTransmission(6);
			productG.setBodyType(SUV);

			Product productH = new Product();
			productH.setModelName("Car H");
			productH.setYear(2021);
			productH.setPrice(5000D);
			productA.setCurrencyValue(defaultCurrencyValue);
			productH.setManufacturer(bmw);
			productH.setColor(white);
			productH.setProductCode("PC08");
			productH.setDescription(description);
			productH.setDoors(4);
			productH.setMileage(12);
			productH.setFuelType(hydrogen);
			productH.setEngineSize(2);
			productH.setTransmission(6);
			productH.setBodyType(van);

			listBuilder.add(productA);
			listBuilder.add(productB);
			listBuilder.add(productC);
			listBuilder.add(productD);
			listBuilder.add(productE);
			listBuilder.add(productF);
			listBuilder.add(productG);
			listBuilder.add(productH);
			break;

		default:
			throw new RuntimeException("Invalid Application Profile Found");
		}

		return listBuilder.build();
	}

}
