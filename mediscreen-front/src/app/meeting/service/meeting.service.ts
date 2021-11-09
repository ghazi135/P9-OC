import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {HandleErrorsService} from "../../shared/shared-services/handle-errors.service";
import {Observable, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";
import {Rdv, RdvElement} from "../model/Rdv";

@Injectable({
  providedIn: 'root'
})
export class MeetingService {
  private RdvUrl = 'http://localhost:8081/rdv'
  constructor(
    private http: HttpClient,
    private handleErrorsService: HandleErrorsService
  ) { }

  getMeetings(): Observable<any> {
    return this.http.get(`${this.RdvUrl}`).pipe(
      map(response => response as Array<any>),
      catchError((error: HttpErrorResponse) => {
        this.handleErrorsService.handleError(error.status);
        return throwError(error);
      })
    );
  }

  insertMeeting(meeting: RdvElement): Observable<RdvElement> {
    return this.http.post<RdvElement>(`${this.RdvUrl}`, meeting).pipe(
      map((response) => response as RdvElement),
      catchError((error: HttpErrorResponse) => {
        this.handleErrorsService.handleError(error.status);
        return throwError(error);
      })
    );
  }

  updateRdv(idRdv: number, rdv: RdvElement): Observable<RdvElement> {
    return this.http
      .put<RdvElement>(`${this.RdvUrl}/${idRdv}`,rdv)
      .pipe(
        map((response) => response as RdvElement),
        catchError((error: HttpErrorResponse) => {
          this.handleErrorsService.handleError(error.status);
          return throwError(error);
        })
      );
  }

  deletePatient(meetingId: number): Observable<RdvElement> {
    return this.http.delete<RdvElement>(`${this.RdvUrl}/${meetingId}`).pipe(
      map((response) => response as RdvElement),
      catchError((error: HttpErrorResponse) => {
        this.handleErrorsService.handleError(error.status);
        return throwError(error);
      })
    );
  }

}
