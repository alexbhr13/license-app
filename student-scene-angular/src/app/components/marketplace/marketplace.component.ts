import {Component, OnInit} from '@angular/core';
import {EventCard} from "../../interfaces/event-card";
import {Router} from "@angular/router";
import {RestapiService} from "../../services/restapi.service";
import {ItemCard} from "../../interfaces/item-card";
import {EventCardComponent} from "../../generic/event-card/event-card.component";
import {NgForOf} from "@angular/common";
import {ItemCardComponent} from "../../generic/item-card/item-card.component";
import {FooterComponent} from "../../generic/footer/footer.component";

@Component({
  selector: 'app-marketplace',
  standalone: true,
    imports: [
        EventCardComponent,
        NgForOf,
        ItemCardComponent,
        FooterComponent
    ],
  templateUrl: './marketplace.component.html',
  styleUrl: './marketplace.component.css'
})
export class MarketplaceComponent implements OnInit{

  items: ItemCard[] | undefined;
  constructor(private router: Router, private restApiService: RestapiService) {}
  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if(token) {
      this.restApiService.getItems(-1,10)
        .subscribe((data: ItemCard[] | undefined) => {
          console.log(data);
          this.items = data;
        });
    }
  }

}
