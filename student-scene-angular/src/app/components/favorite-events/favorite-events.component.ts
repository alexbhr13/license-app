import {Component, OnInit} from '@angular/core';
import {EventCardComponent} from "../../generic/event-card/event-card.component";
import {FooterComponent} from "../../generic/footer/footer.component";
import {NgForOf} from "@angular/common";
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {RestapiService} from "../../services/restapi.service";
import {EventCard} from "../../interfaces/event-card";

@Component({
  selector: 'app-favorite-events',
  standalone: true,
  imports: [
    EventCardComponent,
    FooterComponent,
    NgForOf,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './favorite-events.component.html',
  styleUrl: './favorite-events.component.css'
})
export class FavoriteEventsComponent implements OnInit{

  favoriteEvents: EventCard[] | undefined;
  constructor(private router: Router, private restApiService: RestapiService) {}

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if(token) {
      this.restApiService.getFavoriteEvents()
        .subscribe(data => {
          console.log(data);
          this.favoriteEvents = data;
        });
    }
  }

}
