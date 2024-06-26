import {Component, OnInit} from '@angular/core';
import {RestapiService} from "../../services/restapi.service";
import {ItemCard} from "../../interfaces/item-card";
import {Tag} from "../../interfaces/tag";
import {FooterComponent} from "../../generic/footer/footer.component";
import {ItemCardComponent} from "../../generic/item-card/item-card.component";
import {NgForOf} from "@angular/common";
import {TagComponent} from "../../generic/tag/tag.component";

@Component({
  selector: 'app-search-item',
  standalone: true,
  imports: [
    FooterComponent,
    ItemCardComponent,
    NgForOf,
    TagComponent
  ],
  templateUrl: './search-item.component.html',
  styleUrl: './search-item.component.css'
})
export class SearchItemComponent implements OnInit{

  constructor(private restApiService: RestapiService) {
  }

  tags: Tag[] | undefined;

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if(token) {
      this.restApiService.getSpecificTags("MARKET_TAG")
        .subscribe((data: Tag[] | undefined) => {
          console.log(data);
          this.tags = data;
        });
    }
  }



}
