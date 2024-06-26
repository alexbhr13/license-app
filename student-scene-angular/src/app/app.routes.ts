import {Routes} from "@angular/router";
import {RegisterComponent} from "./components/register/register.component";
import {LoginComponent} from "./components/login/login.component";
import {HomeComponent} from "./components/home/home.component";
import {ConfirmAccountComponent} from "./components/confirm-account/confirm-account.component";
import {ForgotPasswordComponent} from "./components/forgot-password/forgot-password.component";
import {ForgotPasswordRequestComponent} from "./components/forgot-password-request/forgot-password-request.component";
import {EventCardComponent} from "./generic/event-card/event-card.component";
import {MarketplaceComponent} from "./components/marketplace/marketplace.component";
import {ItemCardComponent} from "./generic/item-card/item-card.component";
import {SearchComponent} from "./components/search/search.component";
import {SearchItemComponent} from "./components/search-item/search-item.component";
import {SearchEventComponent} from "./components/search-event/search-event.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {ProfileDetailsComponent} from "./components/profile-details/profile-details.component";
import {ProfileStCardComponent} from "./components/profile-st-card/profile-st-card.component";
import {CartComponent} from "./components/cart/cart.component";
import {FavoriteItemsComponent} from "./components/favorite-items/favorite-items.component";
import {FavoriteEventsComponent} from "./components/favorite-events/favorite-events.component";
import {CheckoutComponent} from "./components/checkout/checkout.component";
import {AuthGuardService} from "./services/auth-guard.service";

export const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuardService]},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'confirm', component: ConfirmAccountComponent},
  { path: 'pwdres-reset', component: ForgotPasswordComponent},
  { path: 'pwdres-request', component: ForgotPasswordRequestComponent},
  { path: 'event-card', component:EventCardComponent, canActivate: [AuthGuardService]},
  { path: 'marketplace', component: MarketplaceComponent, canActivate: [AuthGuardService]},
  { path: 'item', component: ItemCardComponent, canActivate: [AuthGuardService]},
  { path: 'search', component: SearchComponent, canActivate: [AuthGuardService]},
  { path: 'search-items', component: SearchItemComponent, canActivate: [AuthGuardService]},
  { path: 'search-events', component: SearchEventComponent, canActivate: [AuthGuardService]},
  { path: 'favorite-items', component: FavoriteItemsComponent, canActivate: [AuthGuardService]},
  { path: 'favorite-events', component: FavoriteEventsComponent, canActivate: [AuthGuardService]},
  { path: 'cart', component: CartComponent, canActivate: [AuthGuardService]},
  { path: 'checkout', component: CheckoutComponent, canActivate: [AuthGuardService]},
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuardService]},
  { path: 'profile-details', component: ProfileDetailsComponent, canActivate: [AuthGuardService]},
  { path: 'profile-st-card', component: ProfileStCardComponent, canActivate: [AuthGuardService]},
  { path: '**', redirectTo: 'home'},
]
