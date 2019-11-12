using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace SchoolProject_Api.Models
{
    public partial class SchoolProjectDBContext : DbContext
    {
        public SchoolProjectDBContext()
        {
        }

        public SchoolProjectDBContext(DbContextOptions<SchoolProjectDBContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Assignment> Assignment { get; set; }
        public virtual DbSet<Branch> Branch { get; set; }
        public virtual DbSet<Center> Center { get; set; }
        public virtual DbSet<Class> Class { get; set; }
        public virtual DbSet<ClassMaterial> ClassMaterial { get; set; }
        public virtual DbSet<EduactionSubject> EduactionSubject { get; set; }
        public virtual DbSet<Enroll> Enroll { get; set; }
        public virtual DbSet<Exam> Exam { get; set; }
        public virtual DbSet<PrivateClass> PrivateClass { get; set; }
        public virtual DbSet<PrivateEnroll> PrivateEnroll { get; set; }
        public virtual DbSet<Stage> Stage { get; set; }
        public virtual DbSet<Student> Student { get; set; }
        public virtual DbSet<Subject> Subject { get; set; }
        public virtual DbSet<Teacher> Teacher { get; set; }
        public virtual DbSet<TeachingSubjectEs> TeachingSubjectEs { get; set; }
        public virtual DbSet<User> User { get; set; }
        public virtual DbSet<UserTypeId> UserTypeId { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            //if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseSqlServer("Data Source=OmarMetwallyPC;Initial Catalog=SchoolProject_DB;Integrated Security=True");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Assignment>(entity =>
            {
                entity.HasKey(e => new { e.AssignmentId, e.ClassId });

                entity.Property(e => e.AssignmentId)
                    .HasColumnName("AssignmentID")
                    .ValueGeneratedOnAdd();

                entity.Property(e => e.Note).HasColumnName("note");

                entity.HasOne(d => d.Class)
                    .WithMany(p => p.Assignment)
                    .HasForeignKey(d => d.ClassId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Assignment_Class_FK");
            });

            modelBuilder.Entity<Branch>(entity =>
            {
                entity.HasKey(e => e.Bid);

                entity.Property(e => e.Bid).ValueGeneratedNever();

                entity.Property(e => e.Address)
                    .IsRequired()
                    .HasMaxLength(50);

                entity.Property(e => e.Description).HasMaxLength(100);

                entity.HasOne(d => d.C)
                    .WithMany(p => p.Branch)
                    .HasForeignKey(d => d.Cid)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Branch_Center");
            });

            modelBuilder.Entity<Center>(entity =>
            {
                entity.HasKey(e => e.Cid);

                entity.Property(e => e.Cid).ValueGeneratedNever();

                entity.Property(e => e.Description).HasColumnType("text");

                entity.Property(e => e.Rate).HasColumnType("decimal(2, 0)");

                entity.HasOne(d => d.C)
                    .WithOne(p => p.Center)
                    .HasForeignKey<Center>(d => d.Cid)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Center_User");

                entity.HasOne(d => d.UserType)
                    .WithMany(p => p.Center)
                    .HasForeignKey(d => d.UserTypeId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Center_UserTypeId");
            });

            modelBuilder.Entity<Class>(entity =>
            {
                entity.Property(e => e.ClassId).ValueGeneratedNever();

                entity.Property(e => e.ClassName)
                    .IsRequired()
                    .HasMaxLength(50);

                entity.Property(e => e.Duration).HasColumnType("time(0)");

                entity.Property(e => e.Location)
                    .IsRequired()
                    .HasColumnName("location");

                entity.Property(e => e.Price).HasColumnType("decimal(18, 0)");

                entity.Property(e => e.StartDate).HasColumnType("date");

                entity.HasOne(d => d.Center)
                    .WithMany(p => p.Class)
                    .HasForeignKey(d => d.CenterId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Class_Center");

                entity.HasOne(d => d.Subject)
                    .WithMany(p => p.Class)
                    .HasForeignKey(d => d.SubjectId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Class_EduactionSubject");

                entity.HasOne(d => d.Teacher)
                    .WithMany(p => p.Class)
                    .HasForeignKey(d => d.TeacherId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Class_Teacher");
            });

            modelBuilder.Entity<ClassMaterial>(entity =>
            {
                entity.HasKey(e => new { e.PdfId, e.ClassId });

                entity.Property(e => e.PdfId).ValueGeneratedOnAdd();

                entity.Property(e => e.MaterialName)
                    .IsRequired()
                    .HasMaxLength(50);

                entity.Property(e => e.PdfLinks).IsRequired();

                entity.HasOne(d => d.Class)
                    .WithMany(p => p.ClassMaterial)
                    .HasForeignKey(d => d.ClassId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("ClassMaterial_Class_FK");
            });

            modelBuilder.Entity<EduactionSubject>(entity =>
            {
                entity.HasKey(e => e.Esid);

                entity.Property(e => e.Esid)
                    .HasColumnName("ESId")
                    .ValueGeneratedNever();

                entity.HasOne(d => d.Stage)
                    .WithMany(p => p.EduactionSubject)
                    .HasForeignKey(d => d.StageId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_EduactionSubject_Stage");

                entity.HasOne(d => d.Subject)
                    .WithMany(p => p.EduactionSubject)
                    .HasForeignKey(d => d.SubjectId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_EduactionSubject_Subject");
            });

            modelBuilder.Entity<Enroll>(entity =>
            {
                entity.HasKey(e => new { e.StudentId, e.ClassId });

                entity.Property(e => e.EnrollDate).HasColumnType("date");

                entity.HasOne(d => d.Student)
                    .WithMany(p => p.Enroll)
                    .HasForeignKey(d => d.StudentId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Enroll_Student");
            });

            modelBuilder.Entity<Exam>(entity =>
            {
                entity.HasIndex(e => e.ExamId)
                    .HasName("Exam_ExamId_IDX")
                    .IsUnique();

                entity.Property(e => e.ExamDate).HasColumnType("date");

                entity.Property(e => e.ExamPdf).HasColumnType("text");

                entity.Property(e => e.SolutionPdf).HasColumnType("text");

                entity.HasOne(d => d.Class)
                    .WithMany(p => p.Exam)
                    .HasForeignKey(d => d.ClassId)
                    .HasConstraintName("Exam_Class_FK");
            });

            modelBuilder.Entity<PrivateClass>(entity =>
            {
                entity.Property(e => e.PrivateClassId).ValueGeneratedNever();

                entity.Property(e => e.ClassName)
                    .IsRequired()
                    .HasMaxLength(50);

                entity.Property(e => e.Duration).HasColumnType("time(0)");

                entity.Property(e => e.Location)
                    .IsRequired()
                    .HasColumnName("location");

                entity.Property(e => e.Price).HasColumnType("decimal(18, 0)");

                entity.Property(e => e.StartDate).HasColumnType("date");

                entity.HasOne(d => d.Subject)
                    .WithMany(p => p.PrivateClass)
                    .HasForeignKey(d => d.SubjectId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_PrivateClass_Subject");

                entity.HasOne(d => d.Teacher)
                    .WithMany(p => p.PrivateClass)
                    .HasForeignKey(d => d.TeacherId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_PrivateClass_Teacher");
            });

            modelBuilder.Entity<PrivateEnroll>(entity =>
            {
                entity.HasKey(e => new { e.StudentId, e.PrivateClassId });

                entity.Property(e => e.EnrollDate).HasColumnType("date");

                entity.HasOne(d => d.PrivateClass)
                    .WithMany(p => p.PrivateEnroll)
                    .HasForeignKey(d => d.PrivateClassId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_PrivateEnroll_PrivateClass1");

                entity.HasOne(d => d.Student)
                    .WithMany(p => p.PrivateEnroll)
                    .HasForeignKey(d => d.StudentId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_PrivateEnroll_Student1");
            });

            modelBuilder.Entity<Stage>(entity =>
            {
                entity.Property(e => e.StageId).ValueGeneratedNever();

                entity.Property(e => e.StageName)
                    .IsRequired()
                    .HasMaxLength(25);
            });

            modelBuilder.Entity<Student>(entity =>
            {
                entity.HasKey(e => e.Sid);

                entity.Property(e => e.Sid)
                    .HasColumnName("sid")
                    .ValueGeneratedNever();

                entity.Property(e => e.Esid).HasColumnName("ESId");

                entity.HasOne(d => d.Es)
                    .WithMany(p => p.Student)
                    .HasForeignKey(d => d.Esid)
                    .HasConstraintName("FK_Student_EducationalStage");

                entity.HasOne(d => d.S)
                    .WithOne(p => p.Student)
                    .HasForeignKey<Student>(d => d.Sid)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Student_User");

                entity.HasOne(d => d.UserType)
                    .WithMany(p => p.Student)
                    .HasForeignKey(d => d.UserTypeId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Student_UserTypeId");
            });

            modelBuilder.Entity<Subject>(entity =>
            {
                entity.Property(e => e.SubjectId).ValueGeneratedNever();

                entity.Property(e => e.SubjectName)
                    .IsRequired()
                    .HasMaxLength(15);
            });

            modelBuilder.Entity<Teacher>(entity =>
            {
                entity.HasKey(e => e.Tid);

                entity.Property(e => e.Tid)
                    .HasColumnName("tid")
                    .ValueGeneratedNever();

                entity.Property(e => e.Description).HasColumnType("text");

                entity.Property(e => e.Rank).HasColumnType("decimal(2, 0)");

                entity.HasOne(d => d.T)
                    .WithOne(p => p.Teacher)
                    .HasForeignKey<Teacher>(d => d.Tid)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Teacher_User");

                entity.HasOne(d => d.UserType)
                    .WithMany(p => p.Teacher)
                    .HasForeignKey(d => d.UserTypeId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Teacher_UserTypeId");
            });

            modelBuilder.Entity<TeachingSubjectEs>(entity =>
            {
                entity.HasKey(e => new { e.Tid, e.Es });

                entity.ToTable("TeachingSubjectES");

                entity.Property(e => e.Tid).HasColumnName("tid");

                entity.Property(e => e.Es).HasColumnName("ES");

                entity.HasOne(d => d.C)
                    .WithMany(p => p.TeachingSubjectEs)
                    .HasForeignKey(d => d.Cid)
                    .HasConstraintName("FK_TeachingSubjectES_Center1");

                entity.HasOne(d => d.EsNavigation)
                    .WithMany(p => p.TeachingSubjectEs)
                    .HasForeignKey(d => d.Es)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_TeachingSubjectES_EduactionSubject");

                entity.HasOne(d => d.T)
                    .WithMany(p => p.TeachingSubjectEs)
                    .HasForeignKey(d => d.Tid)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_TeachingSubjectES_Teacher");
            });

            modelBuilder.Entity<User>(entity =>
            {
                entity.HasKey(e => e.Uid);

                entity.HasIndex(e => e.Email)
                    .HasName("Email_User")
                    .IsUnique();

                entity.Property(e => e.Uid).HasDefaultValueSql("(newid())");

                entity.Property(e => e.City)
                    .IsRequired()
                    .HasMaxLength(50);

                entity.Property(e => e.Email)
                    .IsRequired()
                    .HasMaxLength(150)
                    .IsUnicode(false);

                entity.Property(e => e.FirstName)
                    .IsRequired()
                    .HasMaxLength(100)
                    .IsUnicode(false);

                entity.Property(e => e.ForgetPasswordKey).HasMaxLength(10);

                entity.Property(e => e.Image).IsUnicode(false);

                entity.Property(e => e.LastName)
                    .IsRequired()
                    .HasMaxLength(100)
                    .IsUnicode(false);

                entity.Property(e => e.Password).IsRequired();

                entity.Property(e => e.PasswordSalt).IsRequired();

                entity.Property(e => e.Region).HasMaxLength(50);

                entity.Property(e => e.RegistrationData).HasColumnType("datetime");
            });

            modelBuilder.Entity<UserTypeId>(entity =>
            {
                entity.HasKey(e => e.UserTypeId1);

                entity.Property(e => e.UserTypeId1)
                    .HasColumnName("UserTypeId")
                    .ValueGeneratedNever();

                entity.Property(e => e.UserType)
                    .IsRequired()
                    .HasMaxLength(10);
            });
        }
    }
}
