using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Bike.Models;
using System.Web.Security;
namespace Bike.Controllers
{
    public class RegistrationController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }



        [HttpGet]
        public ActionResult LogIn()
        {
            return View();
        }

        [HttpPost]
        [Authorize]
        public ActionResult LogIn(Models.Registartion userr)
        {
            if (ModelState.IsValid)
            {

                FormsAuthentication.SetAuthCookie(userr.Email, false);
                return RedirectToAction("Index", "Home");
            }
            else
            {
                ModelState.AddModelError(" ", "Login details are wrong.");
            }
            return View(userr);
        }


        //registration

        [HttpGet]
        public ActionResult Register()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Register(Models.Registartion user)
        {
            if (ModelState.IsValid)
            {
                BikeInfoEntities ctx = new BikeInfoEntities();
                //ctx.Registartions.Add(user);
                ctx.SaveChanges();
                return RedirectToAction("LogIn", "Registration");
            }


            return View();
        }


        //logout

        public ActionResult LogOut()
        {
            FormsAuthentication.SignOut();
            return RedirectToAction("Index", "Home");
        }

    }
}