import {Component, Input} from '@angular/core';
import {ItemCard} from "../../interfaces/item-card";
import {RestapiService} from "../../services/restapi.service";

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.css'
})
export class CartItemComponent {
  @Input() item!: ItemCard;

  constructor(private restApiService: RestapiService) {
  }
  removeFromCart(id: number) {
    this.restApiService.removeFromCart(id).subscribe(response => {
      console.log(response)
    })
  }

}
