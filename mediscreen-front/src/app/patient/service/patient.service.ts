import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {HandleErrorsService} from 'src/app/shared/shared-services/handle-errors.service';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Patient, PatientElement} from "../model/Patient";

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private patienturl = 'http://localhost:8081/api';

  constructor(
    private http: HttpClient,
    private handleErrorsService: HandleErrorsService
  ) {
  }

  getPatients(): Observable<any> {
    return this.http.get(`${this.patienturl}/patient`).pipe(
      map((response) => response as Array<any>),
      catchError((error: HttpErrorResponse) => {
        this.handleErrorsService.handleError(error.status);
        return throwError(error);
      })
    );
  }

  getPatientById(patientId: number): Observable<PatientElement> {
    return this.http.get<PatientElement>(`${this.patienturl}/patient/${patientId}`).pipe(
      map((response) => response as Patient),
      catchError((error: HttpErrorResponse) => {
        this.handleErrorsService.handleError(error.status);
        return throwError(error);
      })
    );
  }

  insertPatient(patient: PatientElement): Observable<PatientElement> {
    return this.http.post<PatientElement>(`${this.patienturl}/patient`, patient).pipe(
      map((response) => response as PatientElement),
      catchError((error: HttpErrorResponse) => {
        this.handleErrorsService.handleError(error.status);
        return throwError(error);
      })
    );
  }

  updatePatient(idPatient: number, patient: PatientElement): Observable<PatientElement> {
    return this.http
      .put<Patient>(`${this.patienturl}/patient/${idPatient}`, patient)
      .pipe(
        map((response) => response as Patient),
        catchError((error: HttpErrorResponse) => {
          this.handleErrorsService.handleError(error.status);
          return throwError(error);
        })
      );
  }


  deletePatient(PatientId: number): Observable<PatientElement> {
    return this.http.delete<PatientElement>(`${this.patienturl}/patient/${PatientId}`).pipe(
      map((response) => response as Patient),
      catchError((error: HttpErrorResponse) => {
        this.handleErrorsService.handleError(error.status);
        return throwError(error);
      })
    );
  }
}
