import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppRoutingModule,routingComponents } from './app-routing.module';

import { AppComponent } from './app.component';
import { TestComponent } from './test/test.component';
import { UpdateClientComponent } from './client/update-client/update-client.component';
import { UpdateEmployeeComponent } from './employee/update-employee/update-employee.component';
import { ClientHeaderComponent } from './client/client-header/client-header.component';
import { EmployeeHeaderComponent } from './employee/employee-header/employee-header.component';
import { ShowAllClientComponent } from './client/show-all-client/show-all-client.component';
import { ViewClientPageComponent } from './client/view-client-page/view-client-page.component';


@NgModule({
  declarations: [
    AppComponent,
    TestComponent,
	routingComponents,
    UpdateClientComponent,
    UpdateEmployeeComponent,
    ClientHeaderComponent,
    EmployeeHeaderComponent,
    ShowAllClientComponent,
    ViewClientPageComponent
	
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
export class AppModule { }