import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

interface Slide {
    image: string;
    title: string;
    subtitle: string;
    ctaText: string;
    ctaLink: string;
}

@Component({
    selector: 'app-hero-slider',
    standalone: true,
    imports: [CommonModule, RouterModule],
    templateUrl: './hero-slider.component.html',
    styles: []
})
export class HeroSliderComponent implements OnInit, OnDestroy {
    slides: Slide[] = [
        {
            image: 'https://images.unsplash.com/photo-1556228453-efd6c1ff04f6?q=80&w=2070&auto=format&fit=crop',
            title: 'Modern Living Redefined',
            subtitle: 'Discover our premium collection of home essentials.',
            ctaText: 'Shop Collection',
            ctaLink: '/products'
        },
        {
            image: 'https://images.unsplash.com/photo-1522758971460-1d21eed7dc1d?q=80&w=2070&auto=format&fit=crop',
            title: 'Smart Home, Smart Life',
            subtitle: 'Upgrade your lifestyle with the latest tech.',
            ctaText: 'Explore Tech',
            ctaLink: '/products'
        },
        {
            image: 'https://images.unsplash.com/photo-1616486338812-3dadae4b4f9d?q=80&w=2070&auto=format&fit=crop',
            title: 'Comfort Meets Style',
            subtitle: 'Furniture designed for your ultimate relaxation.',
            ctaText: 'View Furniture',
            ctaLink: '/products'
        }
    ];

    currentSlide = 0;
    private intervalId: any;

    ngOnInit() {
        this.startAutoPlay();
    }

    ngOnDestroy() {
        this.stopAutoPlay();
    }

    startAutoPlay() {
        this.intervalId = setInterval(() => {
            this.nextSlide();
        }, 5000);
    }

    stopAutoPlay() {
        if (this.intervalId) {
            clearInterval(this.intervalId);
        }
    }

    nextSlide() {
        this.currentSlide = (this.currentSlide + 1) % this.slides.length;
    }

    prevSlide() {
        this.currentSlide = (this.currentSlide - 1 + this.slides.length) % this.slides.length;
    }

    goToSlide(index: number) {
        this.currentSlide = index;
        this.stopAutoPlay();
        this.startAutoPlay();
    }
}
