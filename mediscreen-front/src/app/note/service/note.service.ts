import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {HandleErrorsService} from "../../shared/shared-services/handle-errors.service";
import {Observable, throwError} from "rxjs";
import {Note, NoteElement} from "../model/Note";
import {catchError, map} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class NoteService {

  private NoteUrl = 'http://localhost:8082/notes';

  constructor(
    private http: HttpClient,
    private handleErrorsService: HandleErrorsService
  ) {}




  getNotesByPatient(lastName: string, firstName: string): Observable<any> {
    return this.http.get(`${this.NoteUrl}/findByLastAndFirstName?&lastName=${lastName}&firstName=${firstName}`).pipe(
      map(response => response as Array<any>),
      catchError((error: HttpErrorResponse) => {
        this.handleErrorsService.handleError(error.status);
        return throwError(error);
      })
    );
  }


  insertNote(note: NoteElement): Observable<NoteElement> {
    return this.http.post<NoteElement>(`${this.NoteUrl}`, note).pipe(
      map((response) => response as NoteElement),
      catchError((error: HttpErrorResponse) => {
        this.handleErrorsService.handleError(error.status);
        return throwError(error);
      })
    );
  }

  deleteNote(noteId: string): Observable<NoteElement> {
    return this.http.delete<NoteElement>(`${this.NoteUrl}/${noteId}`).pipe(
      map((response) => response as Note),
      catchError((error: HttpErrorResponse) => {
        this.handleErrorsService.handleError(error.status);
        return throwError(error);
      })
    );
  }

}
