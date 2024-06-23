import {Component, Input} from '@angular/core';
import {ItemCard} from "../../interfaces/item-card";

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.css'
})
export class CartItemComponent {
  @Input() item!: ItemCard;
}
