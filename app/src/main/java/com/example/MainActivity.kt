package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.R
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AppState
import com.example.ui.viewmodel.NavigationTab
import com.example.ui.viewmodel.NovaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                NovaSkillsApp()
            }
        }
    }
}

@Composable
fun NovaSkillsApp() {
    val viewModel: NovaViewModel = viewModel()
    val appState by viewModel.appState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // App entrance router
        Crossfade(
            targetState = appState,
            animationSpec = tween(1000),
            label = "app_entrance_router"
        ) { state ->
            when (state) {
                AppState.SPLASH -> {
                    SplashScreen(modifier = Modifier.fillMaxSize())
                }
                AppState.MAIN -> {
                    MainAppScaffold(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun MainAppScaffold(viewModel: NovaViewModel) {
    val activeTab by viewModel.activeTab.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NovaBrandAppBar(activeTab = activeTab)
        },
        bottomBar = {
            NovaBottomNavigationBar(
                activeTab = activeTab,
                onTabSelected = { tab -> viewModel.selectTab(tab) }
            )
        },
        contentWindowInsets = WindowInsets.navigationBars
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // High-fidelity content screens transition
            Crossfade(
                targetState = activeTab,
                animationSpec = tween(500),
                label = "nav_tab_crossfade"
            ) { tab ->
                when (tab) {
                    NavigationTab.HOME -> {
                        HomeScreen(
                            viewModel = viewModel,
                            onNavigateToServices = { viewModel.selectTab(NavigationTab.SERVICES) }
                        )
                    }
                    NavigationTab.SERVICES -> {
                        ServicesScreen(viewModel = viewModel)
                    }
                    NavigationTab.GALLERY -> {
                        GalleryScreen(viewModel = viewModel)
                    }
                    NavigationTab.FOUNDER -> {
                        FounderScreen()
                    }
                    NavigationTab.CONTACT -> {
                        ContactScreen()
                    }
                }
            }
        }
    }
}

// ==========================================
// CUSTOM APP BAR - PREMIUM CORPORATE BRANDING
// ==========================================
@Composable
fun NovaBrandAppBar(activeTab: NavigationTab) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            ),
        color = Color.White,
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left block with avatar/logo
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo_official),
                    contentDescription = "Nova Skills micro-logo",
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Nova Skills",
                        color = Color(0xFF1E3A8A), // deep corporate blue
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "Learn. Build. Grow.",
                        color = Color(0xFFF59E0B), // gold subtitle
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }

            // Right active status pill
            Box(
                modifier = Modifier
                    .background(Color(0xFFEFF6FF), RoundedCornerShape(20.dp))
                    .border(1.dp, Color(0x223B82F6), RoundedCornerShape(20.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = when (activeTab) {
                        NavigationTab.HOME -> "Accueil"
                        NavigationTab.SERVICES -> "Solutions"
                        NavigationTab.GALLERY -> "Galerie"
                        NavigationTab.FOUNDER -> "Fondateur"
                        NavigationTab.CONTACT -> "Contact"
                    },
                    color = Color(0xFF2563EB),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// ==========================================
// HIGH-END CUSTOM COORPORATE BOTTOM NAVIGATION BAR
// ==========================================
@Composable
fun NovaBottomNavigationBar(
    activeTab: NavigationTab,
    onTabSelected: (NavigationTab) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(16.dp, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        color = Color.White,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(vertical = 10.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NovaBottomBarTabItem(
                tab = NavigationTab.HOME,
                isActive = activeTab == NavigationTab.HOME,
                icon = Icons.Default.Home,
                label = "Home",
                onClick = { onTabSelected(NavigationTab.HOME) }
            )
            NovaBottomBarTabItem(
                tab = NavigationTab.SERVICES,
                isActive = activeTab == NavigationTab.SERVICES,
                icon = Icons.Default.Build,
                label = "Services",
                onClick = { onTabSelected(NavigationTab.SERVICES) }
            )
            NovaBottomBarTabItem(
                tab = NavigationTab.GALLERY,
                isActive = activeTab == NavigationTab.GALLERY,
                icon = Icons.Default.Star,
                label = "Galerie",
                onClick = { onTabSelected(NavigationTab.GALLERY) }
            )
            NovaBottomBarTabItem(
                tab = NavigationTab.FOUNDER,
                isActive = activeTab == NavigationTab.FOUNDER,
                icon = Icons.Default.Person,
                label = "Fondateur",
                onClick = { onTabSelected(NavigationTab.FOUNDER) }
            )
            NovaBottomBarTabItem(
                tab = NavigationTab.CONTACT,
                isActive = activeTab == NavigationTab.CONTACT,
                icon = Icons.Default.Phone,
                label = "Contact",
                onClick = { onTabSelected(NavigationTab.CONTACT) }
            )
        }
    }
}

@Composable
fun NovaBottomBarTabItem(
    tab: NavigationTab,
    isActive: Boolean,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    val tabColor = if (isActive) Color(0xFF2563EB) else Color(0xFF64748B)
    val indicatorScale by animateFloatAsState(
        targetValue = if (isActive) 1f else 0.1f,
        animationSpec = tween(300),
        label = "tab_pill_scale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .testTag("tab_${label.lowercase()}")
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 6.dp, horizontal = 12.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            // Smooth background pill indicator
            if (isActive) {
                Box(
                    modifier = Modifier
                        .scale(indicatorScale)
                        .size(width = 44.dp, height = 26.dp)
                        .background(Color(0xFFEFF6FF), RoundedCornerShape(14.dp))
                )
            }
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = tabColor,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = tabColor,
            fontSize = 11.sp,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium
        )
    }
}
