import {Time} from "@angular/common";

export interface EventCard {
  // id: number;
  // date: string;
  // time: string;
  // title: string;
  // location: string;
  // author: string;
  // imageURL: string;
  // startDateTime: Date;
  // endDateTime: Date;
  // startTime: Date;
  // duration: Date;
  // address: string;
  // eventLink: string;
  // ticketLink: string;
  // createdBy: string;
  // isDraft: boolean;
  // isFavorite: boolean;
  id: number;
  photo: string;
  title: string;
  description: string;
  startDate: Date;
  endDate: Date;
  startTime: Time;
  duration: Date;
  address: string;
  eventLink: string;
  ticketLink: string;
  adminEmail: string;
  tagName: string;
  eventStatus: string;
}
