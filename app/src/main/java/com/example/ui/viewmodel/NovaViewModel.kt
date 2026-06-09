package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.GalleryItem
import com.example.data.NovaDataProvider
import com.example.data.ServiceItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class AppState {
    SPLASH, MAIN
}

enum class NavigationTab {
    HOME, SERVICES, GALLERY, FOUNDER, CONTACT
}

class NovaViewModel : ViewModel() {

    private val _appState = MutableStateFlow(AppState.SPLASH)
    val appState: StateFlow<AppState> = _appState.asStateFlow()

    private val _activeTab = MutableStateFlow(NavigationTab.HOME)
    val activeTab: StateFlow<NavigationTab> = _activeTab.asStateFlow()

    private val _selectedService = MutableStateFlow<ServiceItem?>(null)
    val selectedService: StateFlow<ServiceItem?> = _selectedService.asStateFlow()

    private val _selectedGalleryItem = MutableStateFlow<GalleryItem?>(null)
    val selectedGalleryItem: StateFlow<GalleryItem?> = _selectedGalleryItem.asStateFlow()

    private val _galleryItems = MutableStateFlow(NovaDataProvider.galleryItems)
    val galleryItems = _galleryItems.asStateFlow()

    private val _selectedCategory = MutableStateFlow("Tous")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    init {
        // Automatically transition from SPLASH to MAIN after 2.5 seconds
        viewModelScope.launch {
            delay(2500)
            _appState.value = AppState.MAIN
        }
    }

    fun selectTab(tab: NavigationTab) {
        _activeTab.value = tab
    }

    fun showServiceDetails(service: ServiceItem?) {
        _selectedService.value = service
    }

    fun showGalleryPreview(item: GalleryItem?) {
        _selectedGalleryItem.value = item
    }

    fun filterGallery(category: String) {
        _selectedCategory.value = category
        if (category == "Tous") {
            _galleryItems.value = NovaDataProvider.galleryItems
        } else {
            _galleryItems.value = NovaDataProvider.galleryItems.filter { it.category == category }
        }
    }

    fun nextGalleryItem() {
        val currentItem = _selectedGalleryItem.value ?: return
        val items = _galleryItems.value
        val currentIndex = items.indexOfFirst { it.id == currentItem.id }
        if (currentIndex != -1 && currentIndex < items.size - 1) {
            _selectedGalleryItem.value = items[currentIndex + 1]
        } else if (items.isNotEmpty()) {
            _selectedGalleryItem.value = items.first() // wrap around
        }
    }

    fun prevGalleryItem() {
        val currentItem = _selectedGalleryItem.value ?: return
        val items = _galleryItems.value
        val currentIndex = items.indexOfFirst { it.id == currentItem.id }
        if (currentIndex > 0) {
            _selectedGalleryItem.value = items[currentIndex - 1]
        } else if (items.isNotEmpty()) {
            _selectedGalleryItem.value = items.last() // wrap around
        }
    }
}
