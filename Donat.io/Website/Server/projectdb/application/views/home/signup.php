<form method="POST">
					<h3 class="text-divider">Sign Up</h3>
					<div class="content">

						<div class="input-group form-group">
							<span class="input-group-addon">
								<i class="material-icons">face</i>
							</span>
							<input type="text" class="form-control" placeholder="Username..." id="username" required />
						</div>

            <div class="input-group form-group">
              <span class="input-group-addon">
                <i class="material-icons">lock_outline</i>
              </span>
              <input type="password" placeholder="Password..." class="form-control" id="pass" required />
            </div>

            <div class="input-group form-group">
              <span class="input-group-addon">
                <i class="material-icons">email</i>
              </span>
              <input type="text" class="form-control" placeholder="Email..." id="email" required
							 data-placement="top" title="Tooltip on top"/>
            </div>

						<div class="input-group form-group">
							<span class="input-group-addon">
								<i class="material-icons">people</i>
							</span>
							<input type="text" class="form-control" placeholder="Organization Name..." id="orgName"
							required />
						</div>

            <div class="input-group form-group">
              <span class="input-group-addon">
                <i class="material-icons">business</i>
              </span>
              <input type="text" class="form-control" placeholder="Organization Registration Number..." id="regNo"
							required />
            </div>
</form>
					<div class="text-center">
						<button class="btn btn-round btn-primary btn-lg" id="form_Sign_Up">Sign Up</button>
						<!-- <a href="#" class="btn btn-primary btn-round btn-lg" role="button" id="form_Sign_Up">Sign Up</a> -->
					</div>

						<div class="footer text-center">
            <a href="<?php echo URL; ?>home/index" class="btn btn-primary btn-round" role="button">Already Registered? Log In</a>
					</div>

			</div>
		</div>
	</div>
</div>
