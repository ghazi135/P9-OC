import {Injectable} from '@angular/core';
import { PopupService } from './popup.service';


@Injectable({
  providedIn: 'root'
})
// @ts-ignore

export class HandleErrorsService {
  constructor(private popupService: PopupService) {
  }

  handleError(errorCode: number) {
    switch (errorCode) {
      case 400: {
        this.popupService.createPopup('is-danger',
          'Erreur de format de demande. <br>  Veuillez contacter votre administrateur si elle se reproduit.');
        break;
      }
      case 401: {
        this.popupService.createPopup('is-danger',
          'Vous n\'avez pas les droits nécessaires pour exécuter cette action.');
        break;
      }
      case 404: {
        this.popupService.createPopup('is-danger',
          'Erreur de récupération des données. <br> Veuillez contacter votre administrateur si elle se reproduit.');
        break;
      }
      case 500: {
        this.popupService.createPopup('is-danger',
          'Erreur inexpliquée. <br> Veuillez contacter votre administrateur si elle se reproduit.');
        break;
      }
      case 504: {
        this.popupService.createPopup('is-danger',
          'Timeout. <br> Vérifiez votre connexion et recommencez.');
        break;
      }
      default: {
        this.popupService.createPopup('is-danger',
          'Erreur inexpliquée. <br> Veuillez contacter votre administrateur si elle se reproduit.');
        break;
      }
    }
  }
}
