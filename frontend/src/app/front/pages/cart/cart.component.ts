import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

interface CartItem {
  id: number;
  name: string;
  price: number;
  image: string;
  quantity: number;
}

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: CartItem[] = [];
  subtotal: number = 0;
  tax: number = 0;
  total: number = 0;

  ngOnInit(): void {
    // Mock data
    this.cartItems = [
      {
        id: 1,
        name: 'HomePulse Premium Washer',
        price: 1299,
        image: 'https://images.unsplash.com/photo-1626806775351-538af4406f42?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80',
        quantity: 1
      },
      {
        id: 2,
        name: 'Smart Refrigerator X1',
        price: 2499,
        image: 'https://images.unsplash.com/photo-1571175443880-49e1d58b794a?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80',
        quantity: 1
      }
    ];
    this.calculateTotals();
  }

  updateQuantity(item: CartItem, change: number): void {
    if (item.quantity + change > 0) {
      item.quantity += change;
      this.calculateTotals();
    }
  }

  removeItem(id: number): void {
    this.cartItems = this.cartItems.filter(item => item.id !== id);
    this.calculateTotals();
  }

  calculateTotals(): void {
    this.subtotal = this.cartItems.reduce((acc, item) => acc + (item.price * item.quantity), 0);
    this.tax = this.subtotal * 0.08; // 8% tax
    this.total = this.subtotal + this.tax;
  }
}
