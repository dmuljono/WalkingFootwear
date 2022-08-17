import { Customer } from "./customer";
import { Product } from "./product";

export class CancelOrder {
    cancelId: number;
    orderId: number;
    product: Product;
    customer: Customer;
    purchaseDate: Date;
    quantity: number;
    reasonForCancel: string;
    totalAmount: number;
}
