import { Component } from '@angular/core';
import {NzTypographyComponent} from "ng-zorro-antd/typography";

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [
    NzTypographyComponent
  ],
  templateUrl: './about.component.html',
  styleUrl: './about.component.css'
})
export class AboutComponent {

}
