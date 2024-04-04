import { Component, OnInit } from '@angular/core';
import {NzTypographyComponent} from "ng-zorro-antd/typography";

@Component({
  selector: 'app-welcome',
  standalone: true,
  templateUrl: './welcome.component.html',
  imports: [
    NzTypographyComponent
  ],
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  welcomeMessage!: string;

  constructor() { }

  ngOnInit() {
    this.welcomeMessage = 'USER, welcome to the pmzi-app!';
  }

}
