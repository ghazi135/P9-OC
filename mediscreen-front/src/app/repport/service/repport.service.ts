import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {HandleErrorsService} from "../../shared/shared-services/handle-errors.service";
import {Observable, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class RepportService {
  private reportUrl = 'http://localhost:8083/api';

  constructor(
    private http: HttpClient,
    private handleErrorsService: HandleErrorsService
  ) {
  }

  getReport(lastName: string, firstName: string): Observable<any> {
    return this.http.get(`${this.reportUrl}/reports/lastNameAndFirstName?&lastName=${lastName}&firstName=${firstName}`).pipe(
      map(response => response as Array<any>),
      catchError((error: HttpErrorResponse) => {
        this.handleErrorsService.handleError(error.status);
        return throwError(error);
      })
    );
  }

}
