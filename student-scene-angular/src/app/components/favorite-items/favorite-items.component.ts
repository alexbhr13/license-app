import {Component, OnInit} from '@angular/core';
import {EventCardComponent} from "../../generic/event-card/event-card.component";
import {FooterComponent} from "../../generic/footer/footer.component";
import {NgForOf} from "@angular/common";
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {EventCard} from "../../interfaces/event-card";
import {RestapiService} from "../../services/restapi.service";
import {ItemCard} from "../../interfaces/item-card";
import {ItemCardComponent} from "../../generic/item-card/item-card.component";

@Component({
  selector: 'app-favorite-items',
  standalone: true,
  imports: [
    EventCardComponent,
    FooterComponent,
    NgForOf,
    RouterLink,
    RouterLinkActive,
    ItemCardComponent
  ],
  templateUrl: './favorite-items.component.html',
  styleUrl: './favorite-items.component.css'
})
export class FavoriteItemsComponent implements OnInit{

  favoriteItems: ItemCard[] | undefined;
  constructor(private router: Router, private restApiService: RestapiService) {}

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if(token) {
      this.restApiService.getFavoriteItems()
        .subscribe(data => {
          console.log(data);
          this.favoriteItems = data;
        });
    }
  }

}
