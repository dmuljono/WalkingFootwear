import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StorageService } from '../_services/storage.service';
const TOKEN_HEADER_KEY = 'Authorization';
@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  constructor(private token: StorageService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.token.getUser().accessToken;
    console.log(token);
    if(token!=null){
      req = req.clone({
        withCredentials: true, headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token)
      });
    }
   
    return next.handle(req);
  }
}

export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true },
];
