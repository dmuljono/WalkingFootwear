import { Product } from "./product";

export class Order {
    orderId: number;
    product: Product;
    purchaseDate: Date;
    quantity: number;
    totalAmount: number;
}
