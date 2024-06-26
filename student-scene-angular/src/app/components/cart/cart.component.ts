import { Component } from '@angular/core';
import {FooterComponent} from "../../generic/footer/footer.component";
import {ItemCardComponent} from "../../generic/item-card/item-card.component";
import {NgForOf} from "@angular/common";
import {ItemCard} from "../../interfaces/item-card";
import {RestapiService} from "../../services/restapi.service";
import {CartItemComponent} from "../../generic/cart-item/cart-item.component";

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    FooterComponent,
    ItemCardComponent,
    NgForOf,
    CartItemComponent
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {

  cartItems: ItemCard[] | undefined;

  constructor(private restApiService: RestapiService) {
  }

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if(token) {
      this.restApiService.getCart()
        .subscribe((data: ItemCard[] | undefined) => {
          console.log(data);
          this.cartItems = data;
        });
    }
  }

}
