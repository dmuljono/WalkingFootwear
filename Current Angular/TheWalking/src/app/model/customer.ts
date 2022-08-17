import { Order } from "./order";

export class Customer {
    customerId: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    address: String;
    orders: Order[];
}
