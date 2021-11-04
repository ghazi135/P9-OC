import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HandleErrorsService} from "../../shared/shared-services/handle-errors.service";

@Injectable({
  providedIn: 'root'
})
export class RepportService {
  private repporturl = 'http://localhost:8083/api';
  constructor(
    private http: HttpClient,
    private handleErrorsService: HandleErrorsService
  ) {

  }
}
