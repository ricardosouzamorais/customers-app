import { CustomerDetailsComponent } from '../customer-details/customer-details.component';
import { Observable } from "rxjs";
import { CustomerService } from "../customer.service";
import { Customer } from "../customer";
import { PhoneState } from "../phone-state";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  customers: Observable<Customer[]>;
  _countryFilter = '';
  _phoneStateFilter: string;

  constructor(private customerService: CustomerService,
    private router: Router) { 
      this._countryFilter = '';
      this._phoneStateFilter = null;
    }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.customers = this.customerService.getCustomersList();
  }

  customerDetails(id: number) {
    this.router.navigate(['details', id]);
  }

  get countryFilter(): string {
    return this._countryFilter;
  }

  set countryFilter(value: string) {
    if (typeof value!='undefined' && value){
      this._countryFilter = value;
      this.customers = this.customerService.getCustomersListByCountry(this._countryFilter);
    } else {
      this.reloadData();
    }
  }

  searchByPhoneState(value: string) {
    if (typeof value!='undefined' && value) {
      if (value.toUpperCase() == "ALL") {
        this.reloadData();
      } else {
        this.customers = this.customerService.getCustomersListByPhoneState(value);
      }
    }
  }

}
