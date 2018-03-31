using System;
using System.ComponentModel.DataAnnotations;

namespace Bike.Models
{
    public class Signup
    {
        [Required(ErrorMessage = "Name Is Required")]

        [Display(Name = "Name")]
        public string Name { get; set; }

        [Required]
        [EmailAddress]
        [Display(Name = "Email")]
        public string Email { get; set; }


        [Required]
        [Display(Name = "Address1")]
        public string Address1 { get; set; }


        [Required]
        [Display(Name = "Address2")]
        public string Address2 { get; set; }


        [Required]
        [StringLength(100, ErrorMessage = "The {0} must be at least {2} characters long.", MinimumLength = 6)]
        [Display(Name = "Password")]
        public string Password { get; set; }
        
        [Display(Name = "Confirm password")]
        [Compare("Password", ErrorMessage = "The password and confirmation password do not match.")]
        public string ConfirmPassword { get; set; }

   


    }
}