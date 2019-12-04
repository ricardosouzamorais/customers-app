import { Customer } from '../customer';
import { Component, OnInit, Input } from '@angular/core';
import { CustomerService } from '../customer.service';
import { CustomerListComponent } from '../customer-list/customer-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {

  id: number;
  customer: Customer;

  constructor(private route: ActivatedRoute,private router: Router,
    private customerService: CustomerService) { }

  ngOnInit() {
    this.customer = new Customer();

    this.id = this.route.snapshot.params['id'];
    
    this.customerService.getCustomer(this.id)
      .subscribe(data => {
        console.log(data)
        this.customer = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['customers']);
  }

}
