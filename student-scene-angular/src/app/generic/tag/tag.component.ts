import {Component, Input} from '@angular/core';
import {ItemCard} from "../../interfaces/item-card";
import {Tag} from "../../interfaces/tag";

@Component({
  selector: 'app-tag',
  standalone: true,
  imports: [],
  templateUrl: './tag.component.html',
  styleUrl: './tag.component.css'
})
export class TagComponent {

  @Input() tag!: Tag;


}
