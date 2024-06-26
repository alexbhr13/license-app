import {HttpClient, HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {Router} from "@angular/router";
import {inject} from "@angular/core";
import {AuthService} from "./auth.service";
import {catchError, map, switchMap, throwError} from "rxjs";


const exemptUrls: string[] = [
  'http://localhost:8080/api/v1/auth/registration',
  'http://localhost:8080/api/v1/pwdres/request',
  'http://localhost:8080/api/v1/pwdres/reset',
  "http://localhost:8080/api/v1/auth/login"
];
export const tokenInterceptor: HttpInterceptorFn = (req, next) => {

  const authService = inject(AuthService);
  const http = inject(HttpClient);
  const router = inject(Router);

  const isExemptUrl = (url: string): boolean => {
    return exemptUrls.some(exemptUrl => url.includes(exemptUrl));
  };

  if (isExemptUrl(req.url)) {
    return next(req);
  }

  const token = localStorage.getItem('token');
  if(token) {
    req = req.clone({
      setHeaders: {
        Authorization: token
      }
    });
  }
  return next(req);

  // const token = localStorage.getItem('token');
  // if (token) {
  //   return http.get('http://localhost:8080/api/v1/auth/validate', { params: { token } }).pipe(
  //     map(() => {
  //       return req.clone({
  //         setHeaders: {
  //           Authorization: `Bearer ${token}`
  //         }
  //       });
  //     }),
  //     catchError((error: HttpErrorResponse) => {
  //       if (error.status === 401) {
  //         authService.logout();
  //         router.navigate(['/login']);
  //       }
  //       return throwError(error);
  //     }),
  //     switchMap(clonedReq => next(clonedReq))
  //   );
  // } else {
  //   return next(req);
  // }

  // const token = localStorage.getItem('token');
  // if(token) {
  //   req = req.clone({
  //     setHeaders: {
  //       Authorization: token
  //     }
  //   });
  // }
  // return next(req);

  // const token = localStorage.getItem('token')
  // if(token) {
  //   return http.get('http://localhost:8080/api/v1/auth/validate', {headers: {token}})
  //     .pipe(
  //       map(() => {
  //         const clonedReq = req.clone({
  //           setHeaders: {
  //             Authorization: `Bearer ${token}`
  //           }
  //         })
  //       }),
  //       catchError((error: HttpErrorResponse) => {
  //         if (error.status === 401) {
  //           authService.logout();
  //           router.navigate(['/login']);
  //         }
  //         return throwError(error)
  //       }),
  //       switchMap(clonedReq => next(clonedReq))
  //     );
  // }else {
  //   return next(req);
  // }

};
