import {Component, Input, OnInit} from '@angular/core';
import {EventCardComponent} from "../../generic/event-card/event-card.component";
import {
  MatCard,
  MatCardActions,
  MatCardAvatar,
  MatCardContent,
  MatCardHeader,
  MatCardImage, MatCardTitle
} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {MatGridList, MatGridTile} from "@angular/material/grid-list";
import {NgForOf} from "@angular/common";
import {EventCard} from "../../interfaces/event-card";
// import {mockEventData} from "../../eventMock";
import {Router, RouterLink} from "@angular/router";
import {RestapiService} from "../../services/restapi.service";
import {HttpResponse} from "@angular/common/http";
import {FooterComponent} from "../../generic/footer/footer.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    MatCard,
    MatCardTitle,
    MatCardHeader,
    MatCardContent,
    MatCardActions,
    MatCardAvatar,
    MatCardImage,
    MatButton,
    MatGridList,
    MatGridTile,
    NgForOf,
    EventCardComponent,
    FooterComponent,
    RouterLink
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  events: EventCard[] | undefined;
  constructor(private router: Router, private restApiService: RestapiService) {}
  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if(token) {
      this.restApiService.getEvents(-1,10)
        .subscribe(data => {
        console.log(data);
        this.events = data;
      });
    }
  }

}
