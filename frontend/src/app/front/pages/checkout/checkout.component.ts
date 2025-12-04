import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { OrderService } from '../../../services/order.service';

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent {
  step: number = 1;
  shipping = {
    firstName: '',
    lastName: '',
    address: '',
    city: '',
    zip: '',
    country: ''
  };
  payment = {
    cardNumber: '',
    expiry: '',
    cvv: ''
  };

  constructor(private orderService: OrderService, private router: Router) { }

  nextStep(): void {
    this.step++;
  }

  prevStep(): void {
    this.step--;
  }

  placeOrder(): void {
    const orderData = {
      shipping: this.shipping,
      payment: this.payment,
      items: [], // Should get items from CartService
      total: 0 // Should calculate total
    };

    this.orderService.createOrder(orderData).subscribe({
      next: () => {
        alert('Order placed successfully!');
        this.router.navigate(['/']);
      },
      error: (err: any) => {
        alert('Order placement failed: ' + (err.error?.message || 'Unknown error'));
      }
    });
  }
}
