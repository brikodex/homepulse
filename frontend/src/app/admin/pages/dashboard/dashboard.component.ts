import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  stats = [
    { title: 'Total Sales', value: '$12,450', icon: 'payments', color: 'bg-green-500' },
    { title: 'New Orders', value: '25', icon: 'shopping_cart', color: 'bg-blue-500' },
    { title: 'Total Products', value: '142', icon: 'inventory_2', color: 'bg-purple-500' },
    { title: 'New Users', value: '12', icon: 'group_add', color: 'bg-orange-500' }
  ];

  recentOrders = [
    { id: '#ORD-001', customer: 'John Doe', date: '2023-10-25', total: '$120.00', status: 'Delivered' },
    { id: '#ORD-002', customer: 'Jane Smith', date: '2023-10-24', total: '$85.50', status: 'Processing' },
    { id: '#ORD-003', customer: 'Mike Johnson', date: '2023-10-24', total: '$350.00', status: 'Shipped' },
    { id: '#ORD-004', customer: 'Sarah Williams', date: '2023-10-23', total: '$65.00', status: 'Pending' }
  ];

  ngOnInit(): void {
    // Fetch real data here
  }

  getStatusColor(status: string): string {
    switch (status) {
      case 'Delivered': return 'text-green-600 bg-green-100';
      case 'Processing': return 'text-blue-600 bg-blue-100';
      case 'Shipped': return 'text-purple-600 bg-purple-100';
      case 'Pending': return 'text-orange-600 bg-orange-100';
      default: return 'text-gray-600 bg-gray-100';
    }
  }
}
