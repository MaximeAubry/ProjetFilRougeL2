USE [master]
GO
/****** Object:  Database [FilRougeL2]    Script Date: 25/03/2014 12:04:06 ******/
CREATE DATABASE [FilRougeL2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'FilRougeL2', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\FilRougeL2.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'FilRougeL2_log', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\FilRougeL2_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [FilRougeL2] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [FilRougeL2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [FilRougeL2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [FilRougeL2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [FilRougeL2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [FilRougeL2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [FilRougeL2] SET ARITHABORT OFF 
GO
ALTER DATABASE [FilRougeL2] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [FilRougeL2] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [FilRougeL2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [FilRougeL2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [FilRougeL2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [FilRougeL2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [FilRougeL2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [FilRougeL2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [FilRougeL2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [FilRougeL2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [FilRougeL2] SET  DISABLE_BROKER 
GO
ALTER DATABASE [FilRougeL2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [FilRougeL2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [FilRougeL2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [FilRougeL2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [FilRougeL2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [FilRougeL2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [FilRougeL2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [FilRougeL2] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [FilRougeL2] SET  MULTI_USER 
GO
ALTER DATABASE [FilRougeL2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [FilRougeL2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [FilRougeL2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [FilRougeL2] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [FilRougeL2]
GO
/****** Object:  Table [dbo].[Article]    Script Date: 25/03/2014 12:04:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Article](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[reference] [varchar](50) NOT NULL,
	[nom] [varchar](50) NOT NULL,
	[couleur] [varchar](50) NOT NULL,
	[quantiteStock] [decimal](18, 0) NOT NULL,
	[unite] [varchar](50) NOT NULL,
	[prixUnitaire] [decimal](18, 0) NOT NULL,
	[actif] [bit] NOT NULL,
 CONSTRAINT [PK_Article] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Client]    Script Date: 25/03/2014 12:04:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Client](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[reference] [varchar](50) NOT NULL,
	[raisonSociale] [varchar](50) NOT NULL,
	[adresse] [varchar](50) NOT NULL,
	[codePostal] [varchar](50) NOT NULL,
	[ville] [varchar](50) NOT NULL,
	[pays] [varchar](50) NOT NULL,
	[actif] [bit] NOT NULL,
 CONSTRAINT [PK_Client] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Commande]    Script Date: 25/03/2014 12:04:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Commande](
	[statutCommande] [nvarchar](50) NOT NULL,
	[delaiExpedition] [bigint] NOT NULL,
	[IdDocument] [bigint] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Commercial]    Script Date: 25/03/2014 12:04:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Commercial](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[reference] [varchar](50) NOT NULL,
	[nom] [varchar](50) NOT NULL,
	[prenom] [varchar](50) NOT NULL,
	[email] [varchar](50) NULL,
	[telephone] [varchar](50) NULL,
	[identifiant] [varchar](50) NOT NULL,
	[motDePasse] [varchar](50) NOT NULL,
	[actif] [bit] NOT NULL,
 CONSTRAINT [PK_Commercial] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Contact]    Script Date: 25/03/2014 12:04:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Contact](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[reference] [varchar](50) NOT NULL,
	[nom] [varchar](50) NOT NULL,
	[prenom] [varchar](50) NOT NULL,
	[email] [varchar](50) NULL,
	[telephone] [varchar](50) NULL,
	[actif] [bit] NOT NULL,
	[IdClient] [bigint] NOT NULL,
	[IdCommercial] [bigint] NOT NULL,
 CONSTRAINT [PK_Contact] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Devis]    Script Date: 25/03/2014 12:04:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Devis](
	[dateDeFinDeValidite] [datetime2](7) NOT NULL,
	[signe] [bit] NOT NULL,
	[remise] [decimal](18, 0) NOT NULL,
	[fraisDeTransport] [decimal](18, 0) NOT NULL,
	[tauxDeTva] [decimal](18, 0) NOT NULL,
	[IdDocument] [bigint] NOT NULL,
	[graduationDeDemande] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Document]    Script Date: 25/03/2014 12:04:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Document](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[dateDeCreation] [datetime2](7) NOT NULL,
	[IdCommercial] [bigint] NOT NULL,
	[IdContact] [bigint] NOT NULL,
	[reference] [nchar](10) NOT NULL,
 CONSTRAINT [PK_Document] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Evenement]    Script Date: 25/03/2014 12:04:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Evenement](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[typeRDV] [varchar](50) NOT NULL,
	[commentaire] [text] NOT NULL,
	[dateDeDebut] [datetime2](7) NOT NULL,
	[dateDeFin] [datetime2](7) NOT NULL,
	[IdCommercial] [bigint] NOT NULL,
	[IdContact] [bigint] NOT NULL,
 CONSTRAINT [PK_Evenement] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LigneDeDocument]    Script Date: 25/03/2014 12:04:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LigneDeDocument](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[numeroDeLigne] [int] NOT NULL,
	[qte] [int] NOT NULL,
	[remise] [decimal](18, 0) NOT NULL,
	[IdDocument] [bigint] NOT NULL,
	[IdArticle] [bigint] NOT NULL,
 CONSTRAINT [PK_LigneDeDocument] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Satisfaction]    Script Date: 25/03/2014 12:04:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Satisfaction](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[note] [int] NOT NULL,
	[commentaire] [text] NOT NULL,
	[IdDocument] [bigint] NOT NULL,
	[IdEvenement] [bigint] NOT NULL,
	[dateSatisfaction] [datetime2](7) NOT NULL,
 CONSTRAINT [PK_Satisfaction] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
ALTER TABLE [dbo].[Commande]  WITH CHECK ADD  CONSTRAINT [FK_Commande_Document] FOREIGN KEY([IdDocument])
REFERENCES [dbo].[Document] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Commande] CHECK CONSTRAINT [FK_Commande_Document]
GO
ALTER TABLE [dbo].[Contact]  WITH CHECK ADD  CONSTRAINT [FK_Contact_Client] FOREIGN KEY([IdClient])
REFERENCES [dbo].[Client] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Contact] CHECK CONSTRAINT [FK_Contact_Client]
GO
ALTER TABLE [dbo].[Contact]  WITH CHECK ADD  CONSTRAINT [FK_Contact_Commercial] FOREIGN KEY([IdCommercial])
REFERENCES [dbo].[Commercial] ([Id])
GO
ALTER TABLE [dbo].[Contact] CHECK CONSTRAINT [FK_Contact_Commercial]
GO
ALTER TABLE [dbo].[Devis]  WITH CHECK ADD  CONSTRAINT [FK_Devis_Document] FOREIGN KEY([IdDocument])
REFERENCES [dbo].[Document] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Devis] CHECK CONSTRAINT [FK_Devis_Document]
GO
ALTER TABLE [dbo].[Document]  WITH CHECK ADD  CONSTRAINT [FK_Document_Commercial] FOREIGN KEY([IdCommercial])
REFERENCES [dbo].[Commercial] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Document] CHECK CONSTRAINT [FK_Document_Commercial]
GO
ALTER TABLE [dbo].[Document]  WITH CHECK ADD  CONSTRAINT [FK_Document_Contact] FOREIGN KEY([IdContact])
REFERENCES [dbo].[Contact] ([Id])
GO
ALTER TABLE [dbo].[Document] CHECK CONSTRAINT [FK_Document_Contact]
GO
ALTER TABLE [dbo].[Evenement]  WITH CHECK ADD  CONSTRAINT [FK_Evenement_Commercial] FOREIGN KEY([IdCommercial])
REFERENCES [dbo].[Commercial] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Evenement] CHECK CONSTRAINT [FK_Evenement_Commercial]
GO
ALTER TABLE [dbo].[Evenement]  WITH CHECK ADD  CONSTRAINT [FK_Evenement_Contact] FOREIGN KEY([IdContact])
REFERENCES [dbo].[Contact] ([Id])
GO
ALTER TABLE [dbo].[Evenement] CHECK CONSTRAINT [FK_Evenement_Contact]
GO
ALTER TABLE [dbo].[LigneDeDocument]  WITH CHECK ADD  CONSTRAINT [FK_LigneDeDocument_Article] FOREIGN KEY([IdArticle])
REFERENCES [dbo].[Article] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[LigneDeDocument] CHECK CONSTRAINT [FK_LigneDeDocument_Article]
GO
ALTER TABLE [dbo].[LigneDeDocument]  WITH CHECK ADD  CONSTRAINT [FK_LigneDeDocument_Document] FOREIGN KEY([IdDocument])
REFERENCES [dbo].[Document] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[LigneDeDocument] CHECK CONSTRAINT [FK_LigneDeDocument_Document]
GO
ALTER TABLE [dbo].[Satisfaction]  WITH CHECK ADD  CONSTRAINT [FK_Satisfaction_Document] FOREIGN KEY([IdDocument])
REFERENCES [dbo].[Document] ([Id])
GO
ALTER TABLE [dbo].[Satisfaction] CHECK CONSTRAINT [FK_Satisfaction_Document]
GO
ALTER TABLE [dbo].[Satisfaction]  WITH CHECK ADD  CONSTRAINT [FK_Satisfaction_Evenement] FOREIGN KEY([IdEvenement])
REFERENCES [dbo].[Evenement] ([Id])
GO
ALTER TABLE [dbo].[Satisfaction] CHECK CONSTRAINT [FK_Satisfaction_Evenement]
GO
USE [master]
GO
ALTER DATABASE [FilRougeL2] SET  READ_WRITE 
GO
