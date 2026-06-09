package com.example.data

import com.example.R

// Data Models representing Nova Skills corporate domain
data class ServiceItem(
    val id: String,
    val title: String,
    val description: String,
    val fullDetails: String,
    val iconName: String,
    val accentColor: Long,
    val isSaasService: Boolean = false
)

data class TestimonialItem(
    val id: String,
    val clientName: String,
    val clientRole: String,
    val reviewText: String,
    val rating: Int,
    val initialLetter: String
)

data class GalleryItem(
    val id: String,
    val title: String,
    val description: String,
    val drawableRes: Int,
    val category: String
)

object NovaDataProvider {
    val services = listOf(
        ServiceItem(
            id = "ai_automation",
            title = "Automatisation IA pour Entreprises",
            description = "Solutions intelligentes pour automatiser vos processus et booster votre productivité de 300%.",
            fullDetails = "Déploiement d'agents IA, d'automatisation de flux de travail complexes (n8n, Make), d'intégration de GPT personnalisés et de chatbots intelligents entraînés sur vos données d'entreprise pour un service client 24/7 de pointe.",
            iconName = "SmartToy",
            accentColor = 0xFF2563EB
        ),
        ServiceItem(
            id = "marketing_funnel",
            title = "Marketing Digital & Funnel de Vente",
            description = "Stratégies digitales avancées pour attirer, convertir et fidéliser vos clients en continu.",
            fullDetails = "Création de tunnels de vente à haute conversion, campagnes publicitaires ciblées (Meta, Google, TikTok Ads), copywriting persuasif, stratégies d'acquisition organique de leads et remarketing prédictif.",
            iconName = "TrendingUp",
            accentColor = 0xFF06B6D4
        ),
        ServiceItem(
            id = "web_dev",
            title = "Développement Web & Mobile Full-Stack",
            description = "Sites internet et applications web modernes, ultra-rapides, adaptatifs et entièrement sécurisés.",
            fullDetails = "Conception sur-mesure d'applications web interactives et plates-formes SaaS performantes en Kotlin, React, et Next.js, avec une architecture cloud robuste et un design centré sur l'utilisateur.",
            iconName = "LaptopMac",
            accentColor = 0xFF8B5CF6
        ),
        ServiceItem(
            id = "tech_coaching",
            title = "Formations Tech & Coaching Digital",
            description = "Formations pratiques d'élite et coaching individuel intensif pour dominer l'ère du digital.",
            fullDetails = "Programmes certifiants pour maîtriser l'ingénierie des prompts AI, la programmation moderne, le no-code avancé et la transformation numérique. Accompagnement stratégique sur mesure.",
            iconName = "School",
            accentColor = 0xFFF59E0B
        ),
        ServiceItem(
            id = "whatsapp_automation",
            title = "Automatisation WhatsApp Business",
            description = "Automatisation et configuration WhatsApp API pour une relation client instantanée et engageante.",
            fullDetails = "Mise en place de webhooks, agents de vente automatisés reliés à des bases de données produits, notifications automatiques de commande et tunnels conversationnels complets directement sur WhatsApp.",
            iconName = "Forum",
            accentColor = 0xFF10B981
        ),
        ServiceItem(
            id = "ai_agency_key",
            title = "Création d'Agences IA Clés en Main",
            description = "Modèle clé en main : nous concevons votre propre agence IA prête à opérer et générer des revenus.",
            fullDetails = "Infrastructures d'agence prêtes avec site web pro, tunnels d'acquisition client automatisés, portefeuilles de services IA packagés, et scripts de vente validés pour lancer votre business d'automatisation IA immédiatement.",
            iconName = "RocketLaunch",
            accentColor = 0xFFEC4899
        ),
        ServiceItem(
            id = "brand_design",
            title = "Design & Branding Professionnel",
            description = "Identité visuelle de marque mémorable : logos, chartes graphiques, flyers et packagings premium.",
            fullDetails = "Développement de concepts créatifs uniques, refonte visuelle complète de marques, design UI/UX pour applications, design publicitaire premium à fort impact pour capter instantanément l'attention.",
            iconName = "Palette",
            accentColor = 0xFF14B8A6
        ),
        ServiceItem(
            id = "saas_recom",
            title = "AI-Powered Course System",
            description = "Notre solution SAAS exclusive de recommandation intelligente de cours et de gestion automatique.",
            fullDetails = "Un système propulsé par l'IA qui segmente le profil de l'apprenant, recommande instantanément les meilleurs modules de compétence pour maximiser ses taux d'embauche et gère dynamiquement l'inscription et le suivi scolaire.",
            iconName = "Analytics",
            accentColor = 0xFF6366F1,
            isSaasService = true
        )
    )

    val testimonials = listOf(
        TestimonialItem(
            id = "t1",
            clientName = "Armel Kamga",
            clientRole = "Directeur de Cabinet de Conseil",
            reviewText = "L'automatisation IA mise en place par Nova Skills a littéralement transformé notre gestion de clientèle. Un gain de temps inestimable !",
            rating = 5,
            initialLetter = "A"
        ),
        TestimonialItem(
            id = "t2",
            clientName = "Dr. Sandrine Ngo",
            clientRole = "Fondatrice d'E-Health Start",
            reviewText = "Une équipe de professionnels hors pair. Le développement web de notre plateforme SaaS est rapide, réactif et le design est à couper le souffle.",
            rating = 5,
            initialLetter = "S"
        ),
        TestimonialItem(
            id = "t3",
            clientName = "Marc Mbarga",
            clientRole = "CEO de Globe Distribution",
            reviewText = "Leurs funnels de vente digitaux nous ont permis de multiplier nos leads par 4 en seulement 2 mois. Recommandé à 100% !",
            rating = 5,
            initialLetter = "M"
        ),
        TestimonialItem(
            id = "t4",
            clientName = "Frank Esso",
            clientRole = "Consultant Indépendant",
            reviewText = "La formation tech intensive proposée par Dimitri m'a donné toutes les clefs nécessaires pour intégrer l'IA dans mes outils quotidiens.",
            rating = 5,
            initialLetter = "F"
        )
    )

    val galleryItems = listOf(
        GalleryItem(
            id = "g1",
            title = "Identité Officielle Nova Skills",
            description = "Logo corporate, symbolisant l'ascension numérique, l'éducation technologique et l'excellence digitale.",
            drawableRes = R.drawable.logo_official,
            category = "Branding"
        ),
        GalleryItem(
            id = "g2",
            title = "Pack Premium - L'Excellence à Portée de Main",
            description = "Affiche de présentation officielle du Pack Premium Nova Skills comprenant l'accès à VEO3, Nano Banana Pro et l'IA Premium.",
            drawableRes = R.drawable.pack_premium,
            category = "Flyers"
        ),
        GalleryItem(
            id = "g3",
            title = "Notre Solution Expert Digital & IA",
            description = "Infographie présentant la matrice entière de services et solutions SaaS propulsées par l'Intelligence Artificielle de Nova Skills.",
            drawableRes = R.drawable.flyer_services,
            category = "Services"
        ),
        GalleryItem(
            id = "g4",
            title = "Founder Dimitri Kuete",
            description = "Portrait professionnel de Dimitri Kuete, fondateur et PDG de Nova Skills, arborant un costume élégant.",
            drawableRes = R.drawable.img_founder_profile_1781020292244,
            category = "Corporate"
        ),
        GalleryItem(
            id = "g5",
            title = "Branding Visuel - Cercle Technologique",
            description = "Enseigne emblématique de Nova Skills avec la signature officielle : Learn. Build. Grow. Digital Education & AI Solutions.",
            drawableRes = R.drawable.hero_branding,
            category = "Branding"
        )
    )
}
