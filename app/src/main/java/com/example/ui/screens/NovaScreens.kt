package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.R
import com.example.data.GalleryItem
import com.example.data.NovaDataProvider
import com.example.data.ServiceItem
import com.example.data.TestimonialItem
import com.example.ui.viewmodel.NavigationTab
import com.example.ui.viewmodel.NovaViewModel

// ==========================================
// CUSTOM HAND-DRAWN BESPOKE SERVICE ICONS
// ==========================================
@Composable
fun CustomServiceIcon(iconName: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(54.dp)
            .background(color.copy(alpha = 0.12f), CircleShape)
            .border(1.dp, color.copy(alpha = 0.25f), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(32.dp)) {
            val width = size.width
            val height = size.height

            when (iconName) {
                "SmartToy" -> { // Robot Face / AI
                    // Head shape
                    drawRoundRect(
                        color = color,
                        topLeft = Offset(width * 0.15f, height * 0.25f),
                        size = androidx.compose.ui.geometry.Size(width * 0.7f, height * 0.55f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(10f, 10f),
                        style = Stroke(width = 3.dp.toPx())
                    )
                    // Antenna stub
                    drawLine(
                        color = color,
                        start = Offset(width * 0.5f, height * 0.25f),
                        end = Offset(width * 0.5f, height * 0.08f),
                        strokeWidth = 3.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                    drawCircle(color = color, radius = 5f, center = Offset(width * 0.5f, height * 0.08f))
                    // Eyes
                    drawCircle(color = color, radius = 4f, center = Offset(width * 0.35f, height * 0.45f))
                    drawCircle(color = color, radius = 4f, center = Offset(width * 0.65f, height * 0.45f))
                    // Smile
                    val mouthPath = Path().apply {
                        moveTo(width * 0.35f, height * 0.65f)
                        quadraticTo(width * 0.5f, height * 0.75f, width * 0.65f, height * 0.65f)
                    }
                    drawPath(
                        path = mouthPath,
                        color = color,
                        style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                "TrendingUp" -> { // Sales Graph
                    // Axis lines
                    drawLine(
                        color = color,
                        start = Offset(width * 0.1f, height * 0.9f),
                        end = Offset(width * 0.1f, height * 0.1f),
                        strokeWidth = 2.5.dp.toPx()
                    )
                    drawLine(
                        color = color,
                        start = Offset(width * 0.1f, height * 0.9f),
                        end = Offset(width * 0.9f, height * 0.9f),
                        strokeWidth = 2.5.dp.toPx()
                    )
                    // Trending line
                    val graphPath = Path().apply {
                        moveTo(width * 0.15f, height * 0.8f)
                        lineTo(width * 0.4f, height * 0.65f)
                        lineTo(width * 0.6f, height * 0.45f)
                        lineTo(width * 0.85f, height * 0.2f)
                    }
                    drawPath(
                        path = graphPath,
                        color = color,
                        style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
                    )
                    // Arrow heads at the end
                    drawLine(
                        color = color,
                        start = Offset(width * 0.85f, height * 0.2f),
                        end = Offset(width * 0.65f, height * 0.2f),
                        strokeWidth = 3.dp.toPx()
                    )
                    drawLine(
                        color = color,
                        start = Offset(width * 0.85f, height * 0.2f),
                        end = Offset(width * 0.85f, height * 0.4f),
                        strokeWidth = 3.dp.toPx()
                    )
                }
                "LaptopMac" -> { // Code Screen / Laptop
                    // Laptop Screen
                    drawRoundRect(
                        color = color,
                        topLeft = Offset(width * 0.15f, height * 0.15f),
                        size = androidx.compose.ui.geometry.Size(width * 0.7f, height * 0.5f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(6f, 6f),
                        style = Stroke(width = 3.dp.toPx())
                    )
                    // Laptop base keyboard area
                    drawLine(
                        color = color,
                        start = Offset(width * 0.05f, height * 0.68f),
                        end = Offset(width * 0.95f, height * 0.68f),
                        strokeWidth = 4.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = color,
                        start = Offset(width * 0.15f, height * 0.68f),
                        end = Offset(width * 0.25f, height * 0.82f),
                        strokeWidth = 4.dp.toPx()
                    )
                    drawLine(
                        color = color,
                        start = Offset(width * 0.85f, height * 0.68f),
                        end = Offset(width * 0.75f, height * 0.82f),
                        strokeWidth = 4.dp.toPx()
                    )
                    drawLine(
                        color = color,
                        start = Offset(width * 0.22f, height * 0.82f),
                        end = Offset(width * 0.78f, height * 0.82f),
                        strokeWidth = 4.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                    // Tiny coder script representation inside screen </
                    val codePath = Path().apply {
                        moveTo(width * 0.35f, height * 0.35f)
                        lineTo(width * 0.25f, height * 0.4f)
                        lineTo(width * 0.35f, height * 0.45f)

                        moveTo(width * 0.48f, height * 0.52f)
                        lineTo(width * 0.52f, height * 0.28f)

                        moveTo(width * 0.65f, height * 0.35f)
                        lineTo(width * 0.75f, height * 0.4f)
                        lineTo(width * 0.65f, height * 0.45f)
                    }
                    drawPath(
                        path = codePath,
                        color = color,
                        style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                "School" -> { // Graduation Cap
                    // Diamond cap top
                    val capPath = Path().apply {
                        moveTo(width * 0.5f, height * 0.15f)
                        lineTo(width * 0.9f, height * 0.38f)
                        lineTo(width * 0.5f, height * 0.6f)
                        lineTo(width * 0.1f, height * 0.38f)
                        close()
                    }
                    drawPath(path = capPath, color = color)
                    // Under Cap skull support
                    val supportPath = Path().apply {
                        moveTo(width * 0.28f, height * 0.52f)
                        lineTo(width * 0.28f, height * 0.72f)
                        quadraticTo(width * 0.5f, height * 0.88f, width * 0.72f, height * 0.72f)
                        lineTo(width * 0.72f, height * 0.52f)
                    }
                    drawPath(path = supportPath, color = color, style = Stroke(width = 3.dp.toPx()))
                    // Tassel cord and fringe
                    val tasselPath = Path().apply {
                        moveTo(width * 0.5f, height * 0.38f)
                        quadraticTo(width * 0.8f, height * 0.5f, width * 0.85f, height * 0.75f)
                    }
                    drawPath(
                        path = tasselPath,
                        color = color,
                        style = Stroke(width = 2.5.dp.toPx(), cap = StrokeCap.Round)
                    )
                    drawCircle(color = color, radius = 4f, center = Offset(width * 0.85f, height * 0.75f))
                }
                "Forum" -> { // WhatsApp Automate bubble
                    // Primary Speech Bubble
                    drawRoundRect(
                        color = color,
                        topLeft = Offset(width * 0.1f, height * 0.15f),
                        size = androidx.compose.ui.geometry.Size(width * 0.65f, height * 0.48f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(8f, 8f),
                        style = Stroke(width = 3.dp.toPx())
                    )
                    val speakTrail = Path().apply {
                        moveTo(width * 0.22f, height * 0.63f)
                        lineTo(width * 0.15f, height * 0.78f)
                        lineTo(width * 0.35f, height * 0.63f)
                    }
                    drawPath(path = speakTrail, color = color)

                    // Secondary overlapping bubble
                    drawRoundRect(
                        color = color,
                        topLeft = Offset(width * 0.38f, height * 0.38f),
                        size = androidx.compose.ui.geometry.Size(width * 0.52f, height * 0.42f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(8f, 8f),
                        style = Stroke(width = 2.dp.toPx())
                    )
                }
                "RocketLaunch" -> { // Rocket Boost Agency
                    // Rocket Base Cone
                    val rocketBody = Path().apply {
                        moveTo(width * 0.5f, height * 0.1f)
                        cubicTo(width * 0.35f, height * 0.4f, width * 0.35f, height * 0.68f, width * 0.35f, height * 0.75f)
                        lineTo(width * 0.65f, height * 0.75f)
                        cubicTo(width * 0.65f, height * 0.68f, width * 0.65f, height * 0.4f, width * 0.5f, height * 0.1f)
                    }
                    drawPath(path = rocketBody, color = color)
                    // Fins
                    val finLeft = Path().apply {
                        moveTo(width * 0.35f, height * 0.62f)
                        lineTo(width * 0.18f, height * 0.8f)
                        lineTo(width * 0.35f, height * 0.75f)
                    }
                    drawPath(path = finLeft, color = color)
                    val finRight = Path().apply {
                        moveTo(width * 0.65f, height * 0.62f)
                        lineTo(width * 0.82f, height * 0.8f)
                        lineTo(width * 0.65f, height * 0.75f)
                    }
                    drawPath(path = finRight, color = color)
                    // Flame paths
                    drawLine(
                        color = color,
                        start = Offset(width * 0.45f, height * 0.78f),
                        end = Offset(width * 0.42f, height * 0.95f),
                        strokeWidth = 3.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = color,
                        start = Offset(width * 0.5f, height * 0.78f),
                        end = Offset(width * 0.5f, height * 0.98f),
                        strokeWidth = 4.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = color,
                        start = Offset(width * 0.55f, height * 0.78f),
                        end = Offset(width * 0.58f, height * 0.95f),
                        strokeWidth = 3.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }
                "Palette" -> { // Designing
                    drawCircle(color = color, radius = width * 0.4f, center = Offset(width * 0.5f, height * 0.5f), style = Stroke(width = 3.dp.toPx()))
                    // Paints dots inside
                    drawCircle(color = color, radius = 5f, center = Offset(width * 0.35f, height * 0.38f))
                    drawCircle(color = color, radius = 5f, center = Offset(width * 0.65f, height * 0.35f))
                    drawCircle(color = color, radius = 5f, center = Offset(width * 0.28f, height * 0.6f))
                    drawCircle(color = color, radius = 5f, center = Offset(width * 0.55f, height * 0.72f))
                    // Thumb cutout
                    drawCircle(color = color, radius = 8f, center = Offset(width * 0.75f, height * 0.62f))
                }
                "Analytics" -> { // SaaS Course System
                    // Drawer Micro Chart Bars
                    drawRect(
                        color = color,
                        topLeft = Offset(width * 0.18f, height * 0.65f),
                        size = androidx.compose.ui.geometry.Size(width * 0.12f, height * 0.25f)
                    )
                    drawRect(
                        color = color,
                        topLeft = Offset(width * 0.38f, height * 0.42f),
                        size = androidx.compose.ui.geometry.Size(width * 0.12f, height * 0.48f)
                    )
                    drawRect(
                        color = color,
                        topLeft = Offset(width * 0.58f, height * 0.25f),
                        size = androidx.compose.ui.geometry.Size(width * 0.12f, height * 0.65f)
                    )
                    drawRect(
                        color = color,
                        topLeft = Offset(width * 0.78f, height * 0.55f),
                        size = androidx.compose.ui.geometry.Size(width * 0.12f, height * 0.35f)
                    )
                    // Trend connector overlay
                    val linePath = Path().apply {
                        moveTo(width * 0.24f, height * 0.65f)
                        lineTo(width * 0.44f, height * 0.42f)
                        lineTo(width * 0.64f, height * 0.25f)
                        lineTo(width * 0.84f, height * 0.55f)
                    }
                    drawPath(
                        path = linePath,
                        color = color,
                        style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                else -> { // Default geometric cube
                    drawRect(
                        color = color,
                        topLeft = Offset(width * 0.25f, height * 0.25f),
                        size = androidx.compose.ui.geometry.Size(width * 0.5f, height * 0.5f),
                        style = Stroke(width = 3.dp.toPx())
                    )
                }
            }
        }
    }
}


// ==========================================
// SCREEN 1: SPLASH SCREEN (Interactive Entrance)
// ==========================================
@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    var startAnimate by remember { mutableStateOf(false) }
    val scaleAnim by animateFloatAsState(
        targetValue = if (startAnimate) 1f else 0.4f,
        animationSpec = tween(1500, easing = EaseOutBack),
        label = "logo_scale"
    )
    val opacityAnim by animateFloatAsState(
        targetValue = if (startAnimate) 1f else 0f,
        animationSpec = tween(1200, easing = EaseIn),
        label = "logo_opacity"
    )

    LaunchedEffect(key1 = true) {
        startAnimate = true
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0B132B), // Very deep midnight blue
                        Color(0xFF1E3A8A), // Deep corporate blue
                        Color(0xFF020617)  // Pitch black bottom core
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            // Elegant glowing logo container
            Box(
                modifier = Modifier
                    .scale(scaleAnim)
                    .drawWithContent {
                        // Soft logo drop shadow
                        drawContent()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_official),
                    contentDescription = "Nova Skills Official Logo",
                    modifier = Modifier
                        .size(174.dp)
                        .clip(RoundedCornerShape(24.dp))
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Brand Header Animation
            Text(
                text = "NOVA SKILLS",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 5.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .scale(scaleAnim)
                    .drawWithContent { drawContent() }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Subtitle tag
            Text(
                text = "LEARN. BUILD. GROW.",
                color = Color(0xFFF59E0B).copy(alpha = opacityAnim), // Gold accent
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 4.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Circular progress indicator styled elegantly
            CircularProgressIndicator(
                color = Color(0xFF60A5FA),
                strokeWidth = 3.dp,
                modifier = Modifier
                    .size(42.dp)
                    .scale(if (startAnimate) 1f else 0.1f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Chargement du portfolio d'excellence...",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 12.sp,
                letterSpacing = 1.sp
            )
        }
    }
}


// ==========================================
// SCREEN 2: HOME SCREEN WITH COMPREHENSIVE SECTIONS
// ==========================================
@Composable
fun HomeScreen(viewModel: NovaViewModel, onNavigateToServices: () -> Unit) {
    val context = LocalContext.current
    var isHeroVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        isHeroVisible = true
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_screen_column"),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // A. Header Branding Visuals (Parallax Card)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFFEFF6FF), Color(0xFFF8FAFC))
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Small official logo watermark
                    Image(
                        painter = painterResource(id = R.drawable.logo_official),
                        contentDescription = "Nova Skills Micro Logo",
                        modifier = Modifier
                            .height(48.dp)
                            .testTag("home_micro_logo")
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Circular Glowing Portrait Profile Frame
                    Box(
                        modifier = Modifier
                            .size(164.dp)
                            .background(
                                Brush.sweepGradient(
                                    colors = listOf(
                                        Color(0xFF2563EB),
                                        Color(0xFF06B6D4),
                                        Color(0xFF10B981),
                                        Color(0xFF2563EB)
                                    )
                                ),
                                CircleShape
                            )
                            .padding(4.dp)
                            .background(Color.White, CircleShape)
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_founder_profile_1781020292244),
                            contentDescription = "Founder Portrait & Logo Badge",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Company Headline
                    Text(
                        text = "VOTRE EXPERT DIGITAL & IA",
                        color = Color(0xFF1E3A8A),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Subheading Intro
                    Text(
                        text = "Transformez votre avenir grâce à l'intelligence artificielle et aux compétences digitales de demain. Entreprenez l'excellence.",
                        color = Color(0xFF475569),
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Button(
                        onClick = onNavigateToServices,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB)),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.height(48.dp)
                    ) {
                        Text(
                            text = "Découvrir nos services",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Services navigation flag",
                            tint = Color.White
                        )
                    }
                }
            }
        }

        // B. Strategic Corporate Cards (Mission, Vision, Values, Story)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "À PROPOS DE NOVA SKILLS",
                    color = Color(0xFF0F172A),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp,
                    modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
                )

                // 1. Mission Card
                AboutInfoCard(
                    title = "Ma Mission",
                    description = "Donner à chacun les outils stratégiques et technologiques indispensables pour dominer et réussir dans le monde en pleine mutation digitale et de l'intelligence artificielle.",
                    gradientColors = listOf(Color(0xFF2563EB), Color(0xFF60A5FA)),
                    icon = Icons.Default.RocketLaunch
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 2. Vision Card
                AboutInfoCard(
                    title = "Notre Vision",
                    description = "Devenir la plateforme d'élite panafricaine de référence en matière de formation pratique continue, de transformation digitale des entreprises, et de création de solutions d'automatisation d'avant-garde.",
                    gradientColors = listOf(Color(0xFF0D9488), Color(0xFF10B981)),
                    icon = Icons.Default.Visibility
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 3. Values Card
                AboutInfoCard(
                    title = "Nos Valeurs",
                    description = "• Innovation Disruptive : Repousser sans cesse les limites de l'IA.\n• Excellence Opérationnelle : Exiger la perfection technique.\n• Accompagnement Personnalisé : Progresser pas à pas.\n• Résultats Concrets : Mesurer le retour sur investissement.",
                    gradientColors = listOf(Color(0xFF7C3AED), Color(0xFF8B5CF6)),
                    icon = Icons.Default.DoneAll
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 4. Creative Profile Showcase
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFFEFF6FF), RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = "Notre Histoire",
                                tint = Color(0xFF2563EB),
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Notre Histoire",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E3A8A)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Fondée par l'entrepreneur numérique Dimitri Kuete, Nova Skills est née du constat que l'Intelligence Artificielle crée une fracture numérique sans précédent. Pour les PME et les professionnels, s'adapter n'est plus une option, c'est une urgence vitale.\n\nNous avons conçu une structure hybride d'agence d'automatisation IA et de centre de micro-formation d'élite pour amener les entreprises à l'indépendance technologique totale avec des formations intensives et de fortes automatisations structurelles.",
                            fontSize = 14.sp,
                            color = Color(0xFF475569),
                            lineHeight = 22.sp
                        )
                    }
                }
            }
        }

        // C. Client Testimonials (Reviews section)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp)
            ) {
                Text(
                    text = "TÉMOIGNAGES CLIENTS",
                    color = Color(0xFF0F172A),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(NovaDataProvider.testimonials) { testimonial ->
                        TestimonialCard(testimonial = testimonial)
                    }
                }
            }
        }
    }
}

@Composable
fun AboutInfoCard(
    title: String,
    description: String,
    gradientColors: List<Color>,
    icon: ImageVector
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Brush.horizontalGradient(gradientColors))
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.White.copy(alpha = 0.2f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                color = Color.White.copy(alpha = 0.95f),
                fontSize = 14.sp,
                lineHeight = 21.sp
            )
        }
    }
}

@Composable
fun TestimonialCard(testimonial: TestimonialItem) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .border(1.dp, Color(0xFFEFF6FF), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            // High-rating gold stars
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                repeat(testimonial.rating) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating Star",
                        tint = Color(0xFFF59E0B),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Review text
            Text(
                text = "${'"'}${testimonial.reviewText}${'"'}",
                color = Color(0xFF475569),
                fontSize = 13.sp,
                lineHeight = 19.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f, fill = false)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Divider(color = Color(0xFFF1F5F9), thickness = 1.dp)

            Spacer(modifier = Modifier.height(10.dp))

            // Author metadata
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color(0xFFEFF6FF), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = testimonial.initialLetter,
                        color = Color(0xFF2563EB),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = testimonial.clientName,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                    Text(
                        text = testimonial.clientRole,
                        fontSize = 11.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            }
        }
    }
}


// ==========================================
// SCREEN 3: SERVICES SECTION (Grid with detail Sheets)
// ==========================================
@Composable
fun ServicesScreen(viewModel: NovaViewModel) {
    val selectedService by viewModel.selectedService.collectAsState()
    val services = NovaDataProvider.services

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("services_screen_list"),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // Core Banner detailing Nova Solution SAAS
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF1E3A8A), Color(0xFF06B6D4))
                            ),
                            RoundedCornerShape(16.dp)
                        )
                        .padding(20.dp)
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "SOLUTIONS SAAS EXCLUSIVES",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "AI-Powered Course Recommendation",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Un système intelligent propulsé par l'IA qui recommande instantanément les meilleurs plans de cours technologiques et gère l'inscription automatisée des apprenants pour Nova Skills.",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 13.sp,
                            lineHeight = 19.sp
                        )
                    }
                }
            }

            item {
                Text(
                    text = "NOTRE SÉLECTION DE SERVICES D'ÉLITE",
                    color = Color(0xFF0F172A),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
                )
            }

            // Services Grid presentation
            items(services.chunked(2)) { pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ServiceGridItem(
                        service = pair[0],
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.showServiceDetails(pair[0]) }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    if (pair.size > 1) {
                        ServiceGridItem(
                            service = pair[1],
                            modifier = Modifier.weight(1f),
                            onClick = { viewModel.showServiceDetails(pair[1]) }
                        )
                    } else {
                        Box(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        // Expanded service detail modal (AlertDialog)
        selectedService?.let { service ->
            ServiceDetailDialog(service = service, onDismiss = { viewModel.showServiceDetails(null) })
        }
    }
}

@Composable
fun ServiceGridItem(service: ServiceItem, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .height(190.dp)
            .clickable(onClick = onClick)
            .border(
                1.dp,
                if (service.isSaasService) Color(0xFF06B6D4).copy(alpha = 0.5f) else Color(0xFFF1F5F9),
                RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                // Customized Brand canvas icon
                CustomServiceIcon(iconName = service.iconName, color = Color(service.accentColor))

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = service.title,
                    color = Color(0xFF0F172A),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 17.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (service.isSaasService) "Solution SAAS" else "En savoir plus",
                    color = Color(service.accentColor),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Expand info arrow",
                    tint = Color(service.accentColor),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun ServiceDetailDialog(service: ServiceItem, onDismiss: () -> Unit) {
    val context = LocalContext.current
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .wrapContentHeight()
                .padding(16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header custom icon and Close Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomServiceIcon(iconName = service.iconName, color = Color(service.accentColor))
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.background(Color(0xFFF1F5F9), CircleShape)
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close dialog")
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = service.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1E3A8A)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = service.fullDetails,
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    color = Color(0xFF475569)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Divider(color = Color(0xFFF1F5F9))

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val whatsappNo = "237682764947"
                        val apiMessage = "Bonjour Nova Skills, je souhaite en savoir plus sur le service d'élite : '${service.title}'."
                        val sendIntent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("https://api.whatsapp.com/send?phone=$whatsappNo&text=${Uri.encode(apiMessage)}")
                        }
                        try {
                            context.startActivity(sendIntent)
                        } catch (e: Exception) {
                            Toast.makeText(context, "L'application WhatsApp n'est pas installée.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Forum,
                        contentDescription = "WhatsApp direct intent symbol",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Commander / En savoir plus",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}


// ==========================================
// SCREEN 4: GALLERY SCREEN WITH FULLSCREEN PREVIEW & SWIPE
// ==========================================
@Composable
fun GalleryScreen(viewModel: NovaViewModel) {
    val items by viewModel.galleryItems.collectAsState()
    val selectedItem by viewModel.selectedGalleryItem.collectAsState()
    val activeCategory by viewModel.selectedCategory.collectAsState()

    val categories = listOf("Tous", "Branding", "Flyers", "Services", "Corporate")

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {
            // Elegant category horizontal selector
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categories) { category ->
                    val isActive = activeCategory == category
                    Box(
                        modifier = Modifier
                            .background(
                                color = if (isActive) Color(0xFF2563EB) else Color(0xFFEFF6FF),
                                shape = RoundedCornerShape(30.dp)
                            )
                            .clickable { viewModel.filterGallery(category) }
                            .padding(horizontal = 18.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = category,
                            color = if (isActive) Color.White else Color(0xFF1E3A8A),
                            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            // Grid displaying flyers
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                            .clickable { viewModel.showGalleryPreview(item) }
                            .border(1.dp, Color(0xFFEFF6FF), RoundedCornerShape(12.dp)),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = painterResource(id = item.drawableRes),
                                contentDescription = item.title,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            // Elegant hover details at the bottom of the grid flyer
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .background(Color.Black.copy(alpha = 0.6f))
                                    .padding(8.dp)
                            ) {
                                Column {
                                    Text(
                                        text = item.title,
                                        color = Color.White,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = item.category,
                                        color = Color(0xFFF59E0B),
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Fullscreen Overlay dialog with swipe & zoom
        selectedItem?.let { item ->
            FullscreenPreviewDialog(
                currentItem = item,
                onDismiss = { viewModel.showGalleryPreview(null) },
                onNext = { viewModel.nextGalleryItem() },
                onPrev = { viewModel.prevGalleryItem() }
            )
        }
    }
}

@Composable
fun FullscreenPreviewDialog(
    currentItem: GalleryItem,
    onDismiss: () -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit
) {
    var zoomScale by remember { mutableStateOf(1f) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.95f))
        ) {
            // Immersive Gallery image with scale support
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 60.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = currentItem.drawableRes),
                    contentDescription = currentItem.title,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .scale(zoomScale),
                    contentScale = ContentScale.Fit
                )
            }

            // Close overlay button
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(18.dp)
                    .background(Color.White.copy(alpha = 0.15f), CircleShape)
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close preview", tint = Color.White)
            }

            // Zoom Interaction actions
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 80.dp)
                    .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(30.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { if (zoomScale > 1f) zoomScale -= 0.5f }) {
                    Icon(imageVector = Icons.Default.Remove, contentDescription = "Zoom out", tint = Color.White)
                }
                Text(
                    text = "Zoom: ${(zoomScale * 100).toInt()}%",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                IconButton(onClick = { if (zoomScale < 3f) zoomScale += 0.5f }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Zoom in", tint = Color.White)
                }
            }

            IconButton(
                onClick = onPrev,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(10.dp)
                    .background(Color.Black.copy(alpha = 0.5f), CircleShape)
            ) {
                Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = "Prev flyer", tint = Color.White, modifier = Modifier.size(36.dp))
            }

            IconButton(
                onClick = onNext,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(10.dp)
                    .background(Color.Black.copy(alpha = 0.5f), CircleShape)
            ) {
                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Next flyer", tint = Color.White, modifier = Modifier.size(36.dp))
            }

            // Title and description details overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(Color.Black.copy(alpha = 0.8f))
                    .padding(16.dp)
            ) {
                Column {
                    Text(currentItem.title, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(currentItem.description, color = Color.LightGray, fontSize = 12.sp, lineHeight = 17.sp)
                }
            }
        }
    }
}


// ==========================================
// SCREEN 5: FOUNDER PAGE (Impressive biography)
// ==========================================
@Composable
fun FounderScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFFF8FAFC))
            .padding(bottom = 80.dp)
    ) {
        // A. Immersive Founder Portrait Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_founder_profile_1781020292244),
                contentDescription = "Dimitri Kuete Workspace Portrait",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Subtle dark radial overlay to make writing readable
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.85f))
                        )
                    )
            )
            // Profile text
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF59E0B), RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "FOUNDER & CEO",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Dimitri Kuete",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Entrepreneur Digital • Expert IA • Formateur Full-Stack",
                    color = Color(0xFF60A5FA),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // B. Personal Statement Quote Block
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(18.dp),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = Icons.Default.FormatQuote,
                    contentDescription = "Quote icon",
                    tint = Color(0xFF2563EB),
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Ma mission est simple : libérer l'homme moderne des tâches répétitives grâce à la puissance des automatisations de l'IA, afin qu'il puisse démultiplier sa valeur ajoutée et transformer ses projets en triomphes.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    color = Color(0xFF1E3A8A),
                    lineHeight = 22.sp
                )
            }
        }

        // C. Biography Details Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "BIOGRAPHIE ET PARCOURS",
                color = Color(0xFF0F172A),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = "Dimitri Kuete est un pionnier de la scène entrepreneuriale numérique et technologique. Passionné d'informatique théorique et d'architectures d'Intelligence Artificielle de pointe, il s'est spécialisé dans le développement intégral de plateformes cloud et l'automatisation à haut rendement de processus d'affaires.\n\nÀ travers Nova Skills, il réunit ses deux forces majeures : délivrer des formations informatiques et IA haut de gamme certifiantes d'une part, et concevoir des intégrations d'automatisation IA clefs en main pour les PME d'autre part.",
                fontSize = 14.sp,
                color = Color(0xFF475569),
                lineHeight = 22.sp,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(24.dp))

            // D. Timeline Milestone Entries
            Text(
                text = "DISTINCTIONS & RÉALISATIONS",
                color = Color(0xFF0F172A),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            MilestoneItem(
                year = "Leader d'Inspiration",
                title = "+500 Professionnels Formés",
                description = "Ingénierie des Prompts IA, automatisation, no-code, et développement informatique moderne maîtrisés par ses élèves."
            )
            MilestoneItem(
                year = "Expert Solutions",
                title = "+50 Entreprises Automatisées",
                description = "Mise en place de flux n8n complexes interconnectés par API, automatisant le support client, la gestion comptable et la logistique."
            )
            MilestoneItem(
                year = "Technologue",
                title = "Concepteur exclusif du SaaS Recom IA",
                description = "Une architecture de pointe pour segmenter automatiquement les étudiants et leur assigner les meilleures compétences d'avenir."
            )
        }
    }
}

@Composable
fun MilestoneItem(year: String, title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.width(90.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF2563EB), RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = year,
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E3A8A)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color(0xFF475569),
                lineHeight = 18.sp
            )
        }
    }
}


// ==========================================
// SCREEN 6: CONTACT & PORTFOLIO ACTION TRIGGERS
// ==========================================
@Composable
fun ContactScreen() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFFF8FAFC))
            .padding(horizontal = 20.dp)
            .padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Cyber header visual
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFEFF6FF), RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Business,
                    contentDescription = "Nova center office",
                    tint = Color(0xFF2563EB),
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "NOVA SKILLS",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1E3A8A)
                )
                Text(
                    text = "Digital Education & AI Solutions Office",
                    fontSize = 13.sp,
                    color = Color(0xFF64748B),
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Disponible partout en ligne pour propulser vos projets à l'échelle internationale.",
                    fontSize = 12.sp,
                    color = Color(0xFF475569),
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "CANAUX DE CONTACT RAPIDE",
            color = Color(0xFF0F172A),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 12.dp)
        )

        // 1. One-click WhatsApp action trigger
        ContactActionButton(
            title = "Discuter sur WhatsApp",
            subtitle = "+237 682 764 947 (WhatsApp Business)",
            icon = Icons.Default.Forum,
            buttonColor = Color(0xFF25D366),
            textColor = Color.White,
            onClick = {
                val whatsappNo = "237682764947"
                val apiMessage = "Bonjour Dimitri, j'ai découvert ton application Nova Skills et j'aimerais parler de mon projet."
                val sendIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://api.whatsapp.com/send?phone=$whatsappNo&text=${Uri.encode(apiMessage)}")
                }
                try {
                    context.startActivity(sendIntent)
                } catch (e: Exception) {
                    Toast.makeText(context, "WhatsApp n'est pas configuré sur ce terminal.", Toast.LENGTH_SHORT).show()
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 2. Direct Call Action Trigger
        ContactActionButton(
            title = "Passer un Appel Direct",
            subtitle = "+237 682 764 947",
            icon = Icons.Default.PhoneInTalk,
            buttonColor = Color(0xFF2563EB),
            textColor = Color.White,
            onClick = {
                val callIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:+237682764947")
                }
                context.startActivity(callIntent)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 3. Email trigger
        ContactActionButton(
            title = "Envoyer un E-mail",
            subtitle = "dimitrikuete400@gmail.com",
            icon = Icons.Default.Mail,
            buttonColor = Color(0xFF0F172A),
            textColor = Color.White,
            onClick = {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:dimitrikuete400@gmail.com")
                    putExtra(Intent.EXTRA_SUBJECT, "Demande d'informations - Nova Skills")
                }
                try {
                    context.startActivity(emailIntent)
                } catch (e: Exception) {
                    Toast.makeText(context, "Aucun client de messagerie installé.", Toast.LENGTH_SHORT).show()
                }
            }
        )

        Spacer(modifier = Modifier.height(26.dp))

        // Social Networks
        Text(
            text = "SUIVEZ-NOUS",
            color = Color(0xFF0F172A),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            SocialMediaIcon(
                icon = Icons.Default.Language,
                label = "Web",
                onClick = {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ais-dev-g54uf3atuniyaiezri3lol-902185521598.europe-west2.run.app"))
                    context.startActivity(browserIntent)
                }
            )
            SocialMediaIcon(
                icon = Icons.Default.Share,
                label = "LinkedIn",
                onClick = {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Rejoignez Nova Skills et Dimitri Kuete pour propulser vos automatisations IA !")
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Partager Nova Skillsvia"))
                }
            )
            SocialMediaIcon(
                icon = Icons.Default.Stars,
                label = "TikTok",
                onClick = {
                    Toast.makeText(context, "Visitez Nova Skills sur TikTok !", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
fun ContactActionButton(
    title: String,
    subtitle: String,
    icon: ImageVector,
    buttonColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .border(1.dp, Color(0xFFEFF6FF), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(buttonColor.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = buttonColor,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Color(0xFF64748B)
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Perform action direction arrow",
                tint = Color(0xFF94A3B8),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun SocialMediaIcon(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFEFF6FF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color(0xFF2563EB),
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = Color(0xFF475569),
            fontWeight = FontWeight.Medium
        )
    }
}
