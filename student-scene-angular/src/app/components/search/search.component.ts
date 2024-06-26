import { Component } from '@angular/core';
import {FooterComponent} from "../../generic/footer/footer.component";
import {ItemCardComponent} from "../../generic/item-card/item-card.component";
import {NgForOf} from "@angular/common";
import {RouterLink, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    FooterComponent,
    ItemCardComponent,
    NgForOf,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {

}
