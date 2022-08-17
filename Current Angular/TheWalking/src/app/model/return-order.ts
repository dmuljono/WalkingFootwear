import { Customer } from "./customer";
import { Product } from "./product";

export class ReturnOrder {
    returnId: number;
    orderId: number;
    product: Product;
    customer: Customer;
    purchaseDate: Date;
    quantity: number;
    reasonForReturn: string;
    totalAmount: number;
    returnApproved: boolean;
}
