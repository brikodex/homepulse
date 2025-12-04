import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ProductService } from './../../../../services/product.service';

@Component({
    selector: 'app-product-form',
    standalone: true,
    imports: [CommonModule, FormsModule, RouterModule],
    templateUrl: './product-form.component.html',
    styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
    product: any = {
        name: '',
        description: '',
        price: 0,
        images: '',
        category: 'Appliances'
    };
    isEditMode = false;

    constructor(
        private productService: ProductService,
        private route: ActivatedRoute,
        private router: Router
    ) { }

    ngOnInit(): void {
        const id = this.route.snapshot.paramMap.get('id');
        if (id) {
            this.isEditMode = true;
            this.productService.getProductById(Number(id)).subscribe(data => {
                this.product = data;
            });
        }
    }

    saveProduct(): void {
        if (this.isEditMode) {
            this.productService.updateProduct(this.product.id, this.product).subscribe(() => {
                this.router.navigate(['/admin/products']);
            });
        } else {
            this.productService.createProduct(this.product).subscribe(() => {
                this.router.navigate(['/admin/products']);
            });
        }
    }
}
