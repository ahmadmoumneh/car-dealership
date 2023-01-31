import { Model } from "./model";

export class Vehicle {
    vehicleId: number;
    model: Model;
    vehicleType: string;
    bodyStyle: string;
    transmission: string;
    color: string;
    msrp: number;
    mileage: number;
    salePrice: number;
    vin: number;
    description: string;
    year: number;
    interior: string;
    isSold: boolean;
    isFeatured: boolean;
}