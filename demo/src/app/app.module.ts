import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppRoutingModule, routingComponents } from './app-routing.module';

import { AppComponent } from './app.component';
import { ClientHeaderComponent } from './client/client-header/client-header.component';
import { EmployeeHeaderComponent } from './employee/employee-header/employee-header.component';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    ClientHeaderComponent,
    EmployeeHeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule {
}
