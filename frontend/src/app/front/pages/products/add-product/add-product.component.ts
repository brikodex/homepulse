import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../../../../services/product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  standalone: true,
  imports: [],
})
export class AddProductComponent {
  productForm: FormGroup;
  selectedImage: File | null = null;

  constructor(private fb: FormBuilder, private productService: ProductService) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]],
      image: [null, Validators.required],
    });
  }

  onImageSelected(event: any) {
    this.selectedImage = event.target.files[0];
    this.productForm.patchValue({ image: this.selectedImage });
  }

  onSubmit() {
    if (!this.productForm.valid || !this.selectedImage) return;

    const formData = new FormData();
    formData.append('name', this.productForm.get('name')?.value);
    formData.append('description', this.productForm.get('description')?.value);
    formData.append('price', this.productForm.get('price')?.value);
    formData.append('image', this.selectedImage);

    this.productService.createProduct(formData).subscribe({
      next: () => {
        alert('Produit ajouté avec succès !');
        this.productForm.reset();
        this.selectedImage = null;
      },
      error: (err) => {
        alert('Erreur lors de l\'ajout du produit : ' + err.message);
      },
    });
  }
}
