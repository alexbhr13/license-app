import {Component, OnInit} from '@angular/core';
import {FooterComponent} from "../../generic/footer/footer.component";
import {ItemCardComponent} from "../../generic/item-card/item-card.component";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-profile',
  standalone: true,
    imports: [
        FooterComponent,
        ItemCardComponent,
        NgForOf
    ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{
  ngOnInit(): void {
  }

}
