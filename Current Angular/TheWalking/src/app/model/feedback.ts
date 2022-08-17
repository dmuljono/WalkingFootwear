import { Order } from "../model/order";
import { Customer } from "./customer";
import { Product } from "./product";

export class Feedback {
    feedbackId: number;
    order: Order;
    customer: Customer;
    product: Product
    deliveryOnTime: boolean;
    rating: number;
    comment: string;
    dateCreated: Date;

}
