import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpResponse} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {EventCard} from "../interfaces/event-card";
import {ItemCard} from "../interfaces/item-card";
import {UserProfile} from "../interfaces/user-profile";
import {Tag} from "../interfaces/tag";

@Injectable({
  providedIn: 'root'
})
export class RestapiService {

  private apiURL = 'http://localhost:8080/api/v1';
  constructor(private http: HttpClient) { }

  register(body: any): Observable<HttpResponse<any>> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>("http://localhost:8080/api/v1/auth/registration", body, { headers, observe: 'response'});
  }

  pwdResRequest(email: string): Observable<HttpResponse<any>> {
    const headers = new HttpHeaders({'email': email});
    return this.http.post('http://localhost:8080/api/v1/pwdres/request', null,{headers, observe:'response'});
  }

  pwdResReset(token: string, body: any) {
    const headers = new HttpHeaders({'token': token});
    return this.http.post('http://localhost:8080/api/v1/pwdres/reset',body,{headers, observe:'response'});

  }

  getEvents(
    offset: number,
    pageSize: number,
    startDate?: string,
    endDate?: string,
    tags?: string,
    eventStatus?: string,
    searchInput?: string,
    myEvents?: boolean
  ): Observable<EventCard[]> {
    let params = new HttpParams();
    if (startDate) params = params.set('start_date', startDate);
    if (endDate) params = params.set('end_date', endDate);
    if (tags) params = params.set('tags', tags);
    if (eventStatus) params = params.set('event_status', eventStatus);
    if (searchInput) params = params.set('search_input', searchInput);
    if (myEvents !== undefined) params = params.set('my_events', myEvents.toString());
    if (offset !== undefined) params = params.set('offset', offset.toString());
    if (pageSize !== undefined) params = params.set('page_size', pageSize.toString());

    return this.http.get<EventCard[]>("http://localhost:8080/api/v1/events", { params });
  }

  getItems(
    offset: number,
    pageSize: number,
    tags?: string,
    itemSize?: string,
    searchInput?: string,
    myItems?: boolean
  ): Observable<ItemCard[]> {
    let params = new HttpParams();
    if (tags) params = params.set('tags', tags);
    if (itemSize) params = params.set('event_status', itemSize);
    if (searchInput) params = params.set('search_input', searchInput);
    if (myItems !== undefined) params = params.set('my_events', myItems.toString());
    if (offset !== undefined) params = params.set('offset', offset.toString());
    if (pageSize !== undefined) params = params.set('page_size', pageSize.toString());

    return this.http.get<ItemCard[]>("http://localhost:8080/api/v1/items", { params });
  }

  getFavoriteEvents(): Observable<EventCard[]>{
    return this.http.get<EventCard[]>("http://localhost:8080/api/v1/favorites/events");
  }

  getFavoriteItems(): Observable<ItemCard[]>{
    return this.http.get<ItemCard[]>("http://localhost:8080/api/v1/favorites/items");
  }

  addFavoriteItem(body: ItemCard) {

    return this.http.post<any>("http://localhost:8080/api/v1/favorites/addItem",body);
  }

  addFavoriteEvent(body: EventCard){

    return this.http.post<any>("http://localhost:8080/api/v1/favorites/addEvent",body)
  }

  getAllTags():Observable<Tag[]> {
    return this.http.get<Tag[]>("http://localhost:8080/api/v1/tags");
  }

  getSpecificTags(tagType: string):Observable<Tag[]> {
    let params = new HttpParams();
    params = params.set("tagType", tagType);
    return this.http.get<Tag[]>("http://localhost:8080/api/v1/tags/get", {params:params});
  }

  getEventByID():Observable<EventCard> {
    return this.http.get<EventCard>("http://localhost:8080/api/v1/events/{{id}}");
  }

  getItemByID(id: number):Observable<ItemCard> {
    return this.http.get<ItemCard>("http://localhost:8080/api/v1/items/{{id}}");
  }

  addToCart(id: number):Observable<any> {
    let params = new HttpParams();
    params = params.set("itemId",id);
    return this.http.post<any>("http://localhost:8080/api/v1/cart/add", null,{params, observe: 'response'});
  }

  removeFromCart(id: number):Observable<any> {
    let params = new HttpParams();
    params = params.set("itemId",id);
    return this.http.delete<any>("http://localhost:8080/api/v1/cart/remove", {params, observe: 'response'});
  }

  getCart():Observable<ItemCard[]> {
    return this.http.get<ItemCard[]>("http://localhost:8080/api/v1/cart/get");
  }

  emptyCart():Observable<any> {
    return this.http.delete<any>("http://localhost:8080/api/v1/cart/empty");
  }

  getUserProfile():Observable<UserProfile> {
    return this.http.get<UserProfile>("http://localhost:8080/api/v1/profile/getProfile");
  }

  setUserProfile(body: UserProfile){
    return this.http.post<any>("http://localhost:8080/api/v1/profile/setProfile", body);
  }

  validateToken():Observable<Boolean> {
    return this.http.get<Boolean>("http://localhost:8080/api/v1/auth/validate");
  }




}
