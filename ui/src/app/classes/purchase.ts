import { User } from "./user";

export class Purchase {
    purchaseId: number;
    name: string;
    phone: number;
    email: string;
    street1: string;
    street2: string;
    city: string;
    state: string;
    zipcode: string;
    price: string;
    date: Date;
    salesUser: User;
    purchaseType: string;
}