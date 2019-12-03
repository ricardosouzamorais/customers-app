import { CustomerDetailsComponent } from '../customer-details/customer-details.component';
import { Observable } from "rxjs";
import { CustomerService } from "../customer.service";
import { Customer } from "../customer";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  customers: Observable<Customer[]>;

  constructor(private customerService: CustomerService,
    private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.customers = this.customerService.getCustomersList();
  }

  customerDetails(id: number) {
    this.router.navigate(['details', id]);
  }

}
