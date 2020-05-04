import { Component, OnInit, Inject, InjectionToken, Input, Injectable } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProgressSpinnerMode } from '@angular/material/progress-spinner';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'loading-login',
  templateUrl: './loading-login.component.html',
  styleUrls: ['./loading-login.component.scss']
})
export class LoadingLoginComponent implements OnInit {

  @Input()
  showSpinner: Boolean


  @Input()
  color: ThemePalette = 'primary';
  mode: ProgressSpinnerMode = 'indeterminate';
  value = 50;

  constructor(
  ) { 

  }

  ngOnInit() {
  }

}
