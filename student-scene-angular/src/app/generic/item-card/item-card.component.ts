import {Component, Input} from '@angular/core';
import {EventCard} from "../../interfaces/event-card";
import {ItemCard} from "../../interfaces/item-card";
import {MatButton} from "@angular/material/button";
import {MatCard, MatCardActions, MatCardContent, MatCardImage, MatCardTitle} from "@angular/material/card";
import {RestapiService} from "../../services/restapi.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-item-card',
  standalone: true,
  imports: [
    MatButton,
    MatCard,
    MatCardActions,
    MatCardContent,
    MatCardImage,
    MatCardTitle
  ],
  templateUrl: './item-card.component.html',
  styleUrl: './item-card.component.css'
})
export class ItemCardComponent {

  @Input() item!: ItemCard;

  constructor(private restApiService: RestapiService, private router:Router) {
  }
  addToCart(id: number){
    this.restApiService.addToCart(id).subscribe(response => {
      console.log(response.status)
      if(response.status===200){
        location.reload();
      }
    });
  }

}
