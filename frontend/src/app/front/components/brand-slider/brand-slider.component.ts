import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-brand-slider',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './brand-slider.component.html',
  styles: [`
    .animate-scroll {
      animation: scroll 30s linear infinite;
    }
    @keyframes scroll {
      0% { transform: translateX(0); }
      100% { transform: translateX(-50%); }
    }
  `]
})
export class BrandSliderComponent {
  brands = [
    { name: 'Samsung', logo: 'https://upload.wikimedia.org/wikipedia/commons/2/24/Samsung_Logo.svg' },
    { name: 'LG', logo: 'https://upload.wikimedia.org/wikipedia/commons/b/bf/LG_logo_%282015%29.svg' },
    { name: 'Sony', logo: 'https://upload.wikimedia.org/wikipedia/commons/c/ca/Sony_logo.svg' },
    { name: 'Apple', logo: 'https://upload.wikimedia.org/wikipedia/commons/f/fa/Apple_logo_black.svg' },
    { name: 'Philips', logo: 'https://upload.wikimedia.org/wikipedia/commons/5/52/Philips_logo_new.svg' },
    { name: 'Bosch', logo: 'https://upload.wikimedia.org/wikipedia/commons/b/b0/Bosch-logo.svg' },
    { name: 'Whirlpool', logo: 'https://upload.wikimedia.org/wikipedia/commons/1/1e/Whirlpool_Corporation_Logo.svg' },
    // Duplicate for infinite scroll effect
    { name: 'Samsung', logo: 'https://upload.wikimedia.org/wikipedia/commons/2/24/Samsung_Logo.svg' },
    { name: 'LG', logo: 'https://upload.wikimedia.org/wikipedia/commons/b/bf/LG_logo_%282015%29.svg' },
    { name: 'Sony', logo: 'https://upload.wikimedia.org/wikipedia/commons/c/ca/Sony_logo.svg' },
    { name: 'Apple', logo: 'https://upload.wikimedia.org/wikipedia/commons/f/fa/Apple_logo_black.svg' },
    { name: 'Philips', logo: 'https://upload.wikimedia.org/wikipedia/commons/5/52/Philips_logo_new.svg' },
    { name: 'Bosch', logo: 'https://upload.wikimedia.org/wikipedia/commons/b/b0/Bosch-logo.svg' },
    { name: 'Whirlpool', logo: 'https://upload.wikimedia.org/wikipedia/commons/1/1e/Whirlpool_Corporation_Logo.svg' },
  ];
}
